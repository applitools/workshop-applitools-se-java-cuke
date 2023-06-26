package com.applitools.workshop;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AcmeBankSteps {
  
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void openBrowserAndEyes(Scenario scenario) throws MalformedURLException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @After
    public void cleanUpTest() {
        driver.quit();
    }

    private void waitForAppearance(By locator)
    {
        wait.until(d -> d.findElements(locator).size() > 0);
    }

    @Given("the ACME Bank login page is displayed")
    public void theAcmeBankLoginPageIsDisplayed() {
        driver.get("https://demo.applitools.com");

        waitForAppearance(By.cssSelector("div.logo-w"));
        waitForAppearance(By.id("username"));
        waitForAppearance(By.id("password"));
        waitForAppearance(By.id("log-in"));
        waitForAppearance(By.cssSelector("input.form-check-input"));
    }

    @When("the user enters valid login credentials")
    public void theUserEntersValidLoginCredentials() {
        driver.findElement(By.id("username")).sendKeys("applibot");
        driver.findElement(By.id("password")).sendKeys("I<3VisualTests");
        driver.findElement(By.id("log-in")).click();
    }

    @Then("the ACME Bank main page is displayed")
    public void theAcmeBankMainPageIsDisplayed() {
      
        // Check various page elements
        waitForAppearance(By.cssSelector("div.logo-w"));
        waitForAppearance(By.cssSelector("div.element-search.autosuggest-search-activator > input"));
        waitForAppearance(By.cssSelector("div.avatar-w img"));
        waitForAppearance(By.cssSelector("ul.main-menu"));
        waitForAppearance(By.xpath("//a/span[.='Add Account']"));
        waitForAppearance(By.xpath("//a/span[.='Make Payment']"));
        waitForAppearance(By.xpath("//a/span[.='View Statement']"));
        waitForAppearance(By.xpath("//a/span[.='Request Increase']"));
        waitForAppearance(By.xpath("//a/span[.='Pay Now']"));

        // Check time message
        assertTrue(Pattern.matches(
                "Your nearest branch closes in:( \\d+[hms])+",
                driver.findElement(By.id("time")).getText()));

        // Check menu element names
        List<WebElement> menuElements = driver.findElements(By.cssSelector("ul.main-menu li span"));
        List<String> menuItems = menuElements.stream().map(i -> i.getText().toLowerCase()).toList();
        List<String> expected = Arrays.asList("card types", "credit cards", "debit cards", "lending", "loans", "mortgages");
        assertEquals(expected, menuItems);

        // Check transaction statuses
        List<WebElement> statusElements = driver.findElements(By.xpath("//td[./span[contains(@class, 'status-pill')]]/span[2]"));
        List<String> statusNames = statusElements.stream().map(n -> n.getText().toLowerCase()).toList();
        List<String> acceptableNames = Arrays.asList("complete", "pending", "declined");
        assertTrue(acceptableNames.containsAll(statusNames));
    }
}