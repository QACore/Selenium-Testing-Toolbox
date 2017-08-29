# Selenium Testing Toolbox

[![Maven Central](https://img.shields.io/maven-central/v/com.github.qacore/selenium-testing-toolbox.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.qacore%22%20AND%20a%3A%22selenium-testing-toolbox%22) 
[![Build Status](https://travis-ci.org/QACore/Selenium-Testing-Toolbox.svg?branch=master)](https://travis-ci.org/QACore/Selenium-Testing-Toolbox)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/QACore/Selenium-Testing-Toolbox/blob/master/LICENSE.txt)

This is a framework for writing maintainable [Selenium](https://github.com/SeleniumHQ/selenium) tests that also makes integrating much easier.

## Continuous Integration Builds

| CI Server | OS      | Status | Description |
| --------- | ------- | ------ | ----------- |
| Travis CI | Linux   | [![Build Status](https://travis-ci.org/QACore/Selenium-Testing-Toolbox.svg?branch=master)](https://travis-ci.org/QACore/Selenium-Testing-Toolbox) | Used to perform quick checks on submitted pull requests and for build matrices including JDK 8 and JDK 9 early access builds |

## Setting up

To keep up to date with the latest releases of Selenium Testing Toolbox, modify the following block in the pom.xml:

```xml
<dependencies>
   <dependency>
      <groupId>com.github.qacore</groupId>
      <artifactId>selenium-testing-toolbox</artifactId>
      <version>1.0.0</version>
   </dependency>
</dependencies>
```

## Layers

Selenium Testing Toolbox encourages the use of Page Object and Service Object patterns for writing your test code in such a way that abstracts the functionality of the system you’re testing into it’s own layer so that: the tests interact with the page or service layer and the page or service layer interacts with the system under test.

```
                    _________        ______________________        _____________________
                   |         |      ||                    ||      ||                   ||
 _______       /-> |  PAGES  |  ->  ||                    ||      ||                   ||
|       |     /    |_________|      ||  SELENIUM TESTING  ||      ||   SYSTEM UNDER    ||
| TESTS | ->        _________       ||      TOOLBOX       ||  ->  ||       TEST        ||
|_______|     \    |         |      ||                    ||      ||                   ||
               \-> | SERVICES|  ->  ||                    ||      ||                   ||
                   |_________|      ||____________________||      ||___________________||
                   
```

## Quickstart with Selenium Testing Toolbox

### JUnit Test

You can use the annotation `@Page` to construct your Page Objects easily.

``` java

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverManager;
import com.github.qacore.seleniumtestingtoolbox.adapter.junit.SeleniumTest;
import com.github.qacore.seleniumtestingtoolbox.annotations.Page;

public class GoogleTest extends SeleniumTest {

    @Page
    public GooglePage google;

    @Before
    public void setup() {
        WebDriverManager.setDriver(new ChromeDriver());
    }

    @After
    public void teardown() {
        WebDriverManager.quit();
    }

    @Test
    public void test() {
        google.load()      // Load the page in the current WebDriver
              .isLoaded(); // Throw an Error if the page is not loaded
    }

}

```

## Authors

* **[Leonardo Carmona da Silva]** - *Product Owner and Software Architect* - [LeoCarmona](https://github.com/LeoCarmona) on [LinkedIn](https://www.linkedin.com/in/l3ocarmona/)

See also the list of [contributors](https://github.com/QACore/Selenium-Testing-Toolbox/graphs/contributors) who participated in this project.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.txt](LICENSE.txt) file for details
