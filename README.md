# Applitools Workshop: Selenium Java with Cucumber

This repository is a workshop for learning how to automate AI-backed web tests as part of a typical front-end development workflow.
It provides all the the instructions and example code for the workshop.

The test automation project uses:

* [Java](https://www.java.com/) as the programming language
* [Selenium WebDriver](https://www.selenium.dev/) for browser automation
* [Cucumber-JVM](https://cucumber.io/docs/installation/java/) with [JUnit 5](https://junit.org/junit5/) as the core test framework
* [Google Chrome](https://www.google.com/chrome/downloads/) as the local browser for testing
* [Apache Maven](https://maven.apache.org/index.html) for dependency management
* [Applitools Eyes SDK](https://applitools.com/platform/eyes/) for visual assertions
* [Applitools Ultrafast Grid](https://applitools.com/platform/ultrafast-grid/) for cross-browser execution
* [Applitools Execution Cloud](https://applitools.com/platform/execution-cloud/) for self-healing remote WebDriver sessions

To complete this workshop, you will need:

1. An [Applitools account](https://auth.applitools.com/users/register), which you can register for free.
2. The [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/), version 8 or higher.
3. A good Java editor, such as [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/).
4. [Apache Maven](https://maven.apache.org/download.cgi) (typically bundled with IDEs).
5. An up-to-date version of [Google Chrome](https://www.google.com/chrome/downloads/).
6. A corresponding version of [ChromeDriver](https://chromedriver.chromium.org/downloads).

The `workshop` folder contains full instructions for the workshop.
This repository also has multiple branches:

* The `main` branch in this repository contains the completed example code.
* Each `section/*` branch contains the version of the code for each section of the workshop.

To execute tests, set the `APPLITOOLS_API_KEY` environment variable
to your [account's API key](https://applitools.com/tutorials/guides/getting-started/registering-an-account),
and then run:

```
mvn test
```

If you get stuck, please refer to the example code in this repository to compare its code to yours.
