package com.applitools.workshop;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AcmeBankSteps {
  
    private static String applitoolsApiKey;
    private static BatchInfo batch;
    private static Configuration config;
    private static VisualGridRunner runner;

    private WebDriver driver;
    private Eyes eyes;

    @BeforeAll
    public static void setUpConfigAndRunner() {
        applitoolsApiKey = System.getenv("APPLITOOLS_API_KEY");

        runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));
        batch = new BatchInfo("Applitools Workshop: Selenium Java with Cucumber");

        config = new Configuration();
        config.setApiKey(applitoolsApiKey);
        config.setBatch(batch);

        config.addBrowser(800, 600, BrowserType.CHROME);
        config.addBrowser(1600, 1200, BrowserType.FIREFOX);
        config.addBrowser(1024, 768, BrowserType.SAFARI);
        config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
        config.addDeviceEmulation(DeviceName.Nexus_10, ScreenOrientation.LANDSCAPE);
    }

    @Before
    public void openBrowserAndEyes(Scenario scenario) throws MalformedURLException {
        driver = new ChromeDriver();

        eyes = new Eyes(runner);
        eyes.setConfiguration(config);
        eyes.open(driver, "ACME Bank Web App", scenario.getName());
    }

    @After
    public void cleanUpTest() {
        eyes.closeAsync();
        driver.quit();
    }

    @AfterAll
    public static void printResults() {
        TestResultsSummary allTestResults = runner.getAllTestResults();
        System.out.println(allTestResults);
    }

    @Given("the ACME Bank login page is displayed")
    public void theAcmeBankLoginPageIsDisplayed() {
        driver.get("https://demo.applitools.com");
        eyes.check(Target.window().fully().withName("Login page"));
    }

    @When("the user enters valid login credentials")
    public void theUserEntersValidLoginCredentials() {
        driver.findElement(By.id("username")).sendKeys("applibot");
        driver.findElement(By.id("password")).sendKeys("I<3VisualTests");
        driver.findElement(By.id("log-in")).click();
    }

    @Then("the ACME Bank main page is displayed")
    public void theAcmeBankMainPageIsDisplayed() {

        // Check the visuals of the page
        eyes.check(Target.window().fully().withName("Main page").layout());
        
        // Check time message
        assertTrue(Pattern.matches(
                "Your nearest branch closes in:( \\d+[hms])+",
                driver.findElement(By.id("time")).getText()));
    }
}