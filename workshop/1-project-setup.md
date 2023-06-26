# Section 1: Project Setup

Welcome to the workshop!
In this workshop, you will create a test automation project in Java
with [Cucumber](https://cucumber.io/),
[Selenium WebDriver](https://www.selenium.dev/),
and [Applitools](https://applitools.com/).
Section 1 covers how to set up your project.


## Setting up Selenium WebDriver

To run Selenium-based tests on your machine, you will need to install an appropriate WebDriver executable.
In this workshop, we will use Google Chrome as the browser, so you will need to install [ChromeDriver](https://chromedriver.chromium.org/downloads).
The versions of Chrome and ChromeDriver must match to be compatible.
For example, Chrome v101 requires ChromeDriver v101.

ChromeDriver must be installed into a directory covered by the system `PATH` variable.
Follow the instructions on Selenium's [Driver Location page](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/driver_location/).
On macOS and Linux, the recommended location for the `chromedriver` executable is the /usr/local/bin directory.
You can test that ChromeDriver is working by running the `chromedriver -v` command to print its version.


## Creating the initial project

The easiest way to set up your project is by downloading it directly from GitHub using this link:
https://github.com/applitools/workshop-applitools-se-java-cuke/archive/refs/heads/section/1-project-setup.zip

Make sure to download the version of the code from the branch named `section/1-project-setup` (which is provided in the link above).
This is the starting point.
It will create a mostly-empty project with the required starting structure.
Throughout this workshop, you will add new code to this project to learn by doing.


## Reviewing the project structure

Your initial project should contain the following files:

```
workshop-applitools-se-java-cuke
├── pom.xml
└── src
    └── test
        ├── java
        │   └── com
        │       └── applitools
        │           └── workshop
        │               └── RunCucumberTests.java
        └── resources
            └── com
                └── applitools
                    └── workshop
                        └── AcmeBank.feature
```


### `pom.xml`

This file declares the project information and Maven dependencies.

*Warning:* You might need to change the package versions to bring them up-to-date or to fit your organization's approved version list.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>example-selenium-java-cucumber</artifactId>
    <version>1.0.0</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.cucumber</groupId>
                <artifactId>cucumber-bom</artifactId>
                <version>7.12.1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.junit</groupId>
                <artifactId>junit-bom</artifactId>
                <version>5.9.3</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.0</version>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>com.applitools</groupId>
            <artifactId>eyes-selenium-java5</artifactId>
            <version>5.56.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.12.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>7.12.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite</artifactId>
            <version>1.9.3</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.3</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```


### `RunCucumberTests.java`

This class is the main runner for the Cucumber tests.
We won't need to make any changes to it.

```java
package com.applitools.workshop;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/applitools/workshop")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTests {
}
```


### `AcmeBank.feature`

This feature file will contain the test scenarios that we will write together.
Initially, this file will not have any scenarios.

```gherkin
Feature: ACME Bank

```


## Running tests for the first time

The simplest way to run tests is to run Maven with the "test" goal.
This will build the project and execute all discoverable tests.

There are two ways to run the `mvn test` goal:

1. Through an IDE like IntelliJ IDEA
2. Through the command line by running `mvn test`

Run `mvn test`.
The build phase should pass,
but the test phase should fail because we haven't written any tests yet!
The output should look something like this:

```
% mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------< org.example:example-selenium-java-cucumber >-------------
[INFO] Building example-selenium-java-cucumber 1.0.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ example-selenium-java-cucumber ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/automationpanda/Code/applitools/workshop-applitools-se-java-cuke/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.11.0:compile (default-compile) @ example-selenium-java-cucumber ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ example-selenium-java-cucumber ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.11.0:testCompile (default-testCompile) @ example-selenium-java-cucumber ---
[INFO] Changes detected - recompiling the module! :input tree
[INFO] Compiling 1 source file with javac [debug target 1.8] to target/test-classes
[WARNING] bootstrap class path not set in conjunction with -source 8
[INFO] 
[INFO] --- maven-surefire-plugin:3.1.0:test (default-test) @ example-selenium-java-cucumber ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.applitools.workshop.RunCucumberTests
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.004 s <<< FAILURE! - in com.applitools.workshop.RunCucumberTests
[ERROR] com.applitools.workshop.RunCucumberTests  Time elapsed: 0.004 s  <<< ERROR!
org.junit.platform.suite.engine.NoTestsDiscoveredException: Suite [com.applitools.workshop.RunCucumberTests] did not discover any tests
        at org.junit.platform.suite.engine.SuiteTestDescriptor.computeTestExecutionResult(SuiteTestDescriptor.java:134)
        at org.junit.platform.suite.engine.SuiteTestDescriptor.execute(SuiteTestDescriptor.java:129)
        at org.junit.platform.suite.engine.SuiteTestEngine.lambda$execute$0(SuiteTestEngine.java:73)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        at java.base/java.util.Iterator.forEachRemaining(Iterator.java:133)
        at java.base/java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1845)
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
        at org.junit.platform.suite.engine.SuiteTestEngine.execute(SuiteTestEngine.java:73)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:147)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:127)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:90)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:55)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:102)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:54)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:114)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:86)
        at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:86)
        at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:50)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:184)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:148)
        at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:122)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)

[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Errors: 
[ERROR]   RunCucumberTests » NoTestsDiscovered Suite [com.applitools.workshop.RunCucumberTests] did not discover any tests
[INFO] 
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.266 s
[INFO] Finished at: 2023-06-26T11:22:06-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.1.0:test (default-test) on project example-selenium-java-cucumber: 
[ERROR] 
[ERROR] Please refer to /Users/automationpanda/Code/applitools/workshop-applitools-se-java-cuke/target/surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```

Verify that you can successfully build the project before moving onto the next chapter.
