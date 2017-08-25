package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.mobile.NetworkConnection;

import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsControl;

/**
 * Augmented {@link WebDriver}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see DefaultAugmentedWebDriver
 *
 * @since 1.0.0
 *
 */
public interface AugmentedWebDriver extends WebDriver, AugmentedSearchContext<AugmentedWebElement>, JavascriptExecutor,
                                            AugmentedTakesScreenshot, EventsControl, HasInputDevices,
                                            HasCapabilities, HasTouchScreen, Interactive, 
                                            WebStorage, NetworkConnection, LocationContext,
                                            WrapsDriver {

    /**
     * Open the url page in a new tab.
     *
     * @param url
     *            the url of the page to.
     */
    void getInNewTab(String url);

    /**
     * Open new tab.
     */
    void openNewTab();
    
    @Override
    AugmentedWebDriver.TargetLocator switchTo();

    @Override
    AugmentedWebDriver.Navigation navigate();

    @Override
    AugmentedWebDriver.Options manage();

    /**
     * Augmented {@link WebDriver.Options}.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     *
     * @see DefaultAugmentedWebDriver.DefaultOptions DefaultOptions
     *
     * @since 1.0.0
     *
     */
    interface Options extends WebDriver.Options {

        @Override
        AugmentedWebDriver.Timeouts timeouts();

        @Override
        AugmentedWebDriver.ImeHandler ime();

        @Override
        AugmentedWebDriver.Window window();

    }

    /**
     * Augmented {@link WebDriver.Timeouts}.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     *
     * @see DefaultAugmentedWebDriver.DefaultOptions.DefaultTimeouts DefaultTimeouts
     *
     * @since 1.0.0
     *
     */
    interface Timeouts extends WebDriver.Timeouts {

        @Override
        AugmentedWebDriver.Timeouts implicitlyWait(long time, TimeUnit unit);

        @Override
        AugmentedWebDriver.Timeouts setScriptTimeout(long time, TimeUnit unit);

        @Override
        AugmentedWebDriver.Timeouts pageLoadTimeout(long time, TimeUnit unit);

    }

    /**
     * Augmented {@link WebDriver.TargetLocator}.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     *
     * @see DefaultAugmentedWebDriver.DefaultTargetLocator DefaultTargetLocator
     *
     * @since 1.0.0
     *
     */
    interface TargetLocator extends WebDriver.TargetLocator {

        @Override
        AugmentedWebDriver frame(int index);

        @Override
        AugmentedWebDriver frame(String nameOrId);

        @Override
        AugmentedWebDriver frame(WebElement frameElement);

        @Override
        AugmentedWebDriver parentFrame();

        @Override
        AugmentedWebDriver window(String nameOrHandle);

        @Override
        AugmentedWebDriver defaultContent();

        @Override
        AugmentedAlert alert();

    }

    /**
     * Augmented {@link WebDriver.Navigation}
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     *
     * @see DefaultAugmentedWebDriver.DefaultNavigation DefaultNavigation
     *
     * @since 1.0.0
     *
     */
    interface Navigation extends WebDriver.Navigation {

    }

    /**
     * Augmented {@link WebDriver.ImeHandler}.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     *
     * @see DefaultAugmentedWebDriver.DefaultOptions.DefaultImeHandler DefaultImeHandler
     *
     * @since 1.0.0
     *
     */
    interface ImeHandler extends WebDriver.ImeHandler {

    }

    /**
     * Augmented {@link WebDriver.Window}.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     *
     * @see DefaultAugmentedWebDriver.DefaultOptions.DefaultWindow DefaultWindow
     *
     * @since 1.0.0
     *
     */
    interface Window extends WebDriver.Window {

    }

    /**
     * Augmented {@link Alert}.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     *
     * @see DefaultAugmentedWebDriver.DefaultAlert DefaultAlert
     *
     * @since 1.0.0
     *
     */
    public interface AugmentedAlert extends Alert {

    }

}
