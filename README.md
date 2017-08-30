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
      <version>1.0.1</version>
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

### Page Objects

To construct a Page, extend `AbstractPage` or `AbstractLoginPage`.

You can define the URL of the page by overriding the `getUrl` method. Then, it’s possible to use the `load()` method to load the page, `isLoaded()` to check if the page is loaded and `isOpened()` to verify if the page is loaded.

``` java

package google;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.support.FindBy;

import com.github.qacore.seleniumtestingtoolbox.annotations.Name;
import com.github.qacore.seleniumtestingtoolbox.annotations.PageComponent;
import com.github.qacore.seleniumtestingtoolbox.annotations.PageRepository;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.AbstractPage;
import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebElement;

import google.components.Footer;

public class GooglePage extends AbstractPage<GooglePage> {

    @PageRepository
    public UI      UI;

    @PageComponent
    private Footer footer;

    public GooglePage fill(String q) {
        UI.SEARCH_FIELD.sendKeys(q);

        return this;
    }

    public GooglePage submit() {
        UI.SEARCH_FIELD.submit();

        return this;
    }

    public GooglePage search() {
        UI.GOOGLE_SEARCH.click();

        return this;
    }

    public GooglePage imFeelingLucky() {
        UI.IM_FEELING_LUCKY.click();

        return this;
    }

    public Footer footer() {
        return footer;
    }

    @Override
    public void isLoaded() throws Error {
        assertTrue("The (Search field) is not displayed", UI.SEARCH_FIELD.isDisplayed());
        assertTrue("The (Google search) is not displayed", UI.GOOGLE_SEARCH.isDisplayed());
        assertTrue("The (I'm feeling lucky) search field is not displayed", UI.IM_FEELING_LUCKY.isDisplayed());
    }

    @Override
    public String getUrl() {
        return "https://www.google.com";
    }

    public static class UI {

        @Name("Search field")      @FindBy(id = "lst-ib")
        public AugmentedWebElement SEARCH_FIELD;

        @Name("Google search")     @FindBy(name = "btnK")
        public AugmentedWebElement GOOGLE_SEARCH;

        @Name("I'm feeling lucky") @FindBy(name = "btnI")
        public AugmentedWebElement IM_FEELING_LUCKY;

    }

}

```

``` java

package google.components;

import org.openqa.selenium.support.FindBy;

import com.github.qacore.seleniumtestingtoolbox.annotations.Name;
import com.github.qacore.seleniumtestingtoolbox.annotations.PageRepository;
import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebElement;

public class Footer {

    @PageRepository
    public UI UI;

    public void advertising() {
        UI.ADVERTISING.click();
    }

    public void business() {
        UI.BUSINESS.click();
    }

    public void about() {
        UI.ABOUT.click();
    }

    public void privacy() {
        UI.PRIVACY.click();
    }

    public void terms() {
        UI.TERMS.click();
    }

    public void settings() {
        UI.SETTINGS.click();
    }

    public static class UI {

        @Name("Advertising") @FindBy(xpath = "(//span[@id='fsl']//a)[1]")
        public AugmentedWebElement ADVERTISING;

        @Name("Business")    @FindBy(xpath = "(//span[@id='fsl']//a)[2]")
        public AugmentedWebElement BUSINESS;

        @Name("About")       @FindBy(xpath = "(//span[@id='fsl']//a)[3]")
        public AugmentedWebElement ABOUT;

        @Name("Privacy")     @FindBy(xpath = "(//span[@id='fsr']//a)[1]")
        public AugmentedWebElement PRIVACY;

        @Name("Terms")       @FindBy(xpath = "(//span[@id='fsr']//a)[2]")
        public AugmentedWebElement TERMS;

        @Name("Settings")    @FindBy(xpath = "(//span[@id='fsr']//a)[3]")
        public AugmentedWebElement SETTINGS;

    }

}

```

## Authors

* **[Leonardo Carmona da Silva]** - *Product Owner and Software Architect* - [LeoCarmona](https://github.com/LeoCarmona) on [LinkedIn](https://www.linkedin.com/in/l3ocarmona/)

See also the list of [contributors](https://github.com/QACore/Selenium-Testing-Toolbox/graphs/contributors) who participated in this project.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.txt](LICENSE.txt) file for details
