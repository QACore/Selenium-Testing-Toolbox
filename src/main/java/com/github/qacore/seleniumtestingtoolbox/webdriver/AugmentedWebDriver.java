package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

import lombok.Data;
import lombok.NonNull;

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

        /**
         * Specifies the amount of time the driver should wait when searching for an element if it is not immediately present.
         * <p>
         * When searching for a single element, the driver should poll the page until the element has been found, or this timeout expires before throwing a {@link NoSuchElementException}. When searching for multiple elements, the driver should poll the page until at least one element has been found or this timeout has expired.
         * </p>
         * <p>
         * Increasing the implicit wait timeout should be used judiciously as it will have an adverse effect on test run time, especially when used with slower location strategies like XPath.
         * </p>
         * 
         * @param duration
         *            The amount duration to wait.
         * 
         * @return A self reference.
         */
        AugmentedWebDriver.Timeouts implicitlyWait(Duration duration);

        @Override
        AugmentedWebDriver.Timeouts implicitlyWait(long time, TimeUnit unit);

        /**
         * Retrieves the implicity wait.
         * 
         * @return The implicity wait. Null if infinity or unknown.
         */
        Duration getImplicityWait();

        /**
         * Sets the amount of time to wait for an asynchronous script to finish execution before throwing an error. If the timeout is null, then the script will be allowed to run indefinitely.
         * 
         * @param duration
         *            The amount duration to wait. Null if infinity or unknown.
         * 
         * @return A self reference.
         * 
         * @see JavascriptExecutor#executeAsyncScript(String, Object...)
         */
        AugmentedWebDriver.Timeouts setScriptTimeout(Duration duration);

        @Override
        AugmentedWebDriver.Timeouts setScriptTimeout(long time, TimeUnit unit);

        /**
         * Retrieves the script timeout.
         * 
         * @return The script timeout. Null if infinity or unknown.
         */
        Duration getScriptTimeout();

        /**
         * Sets the amount of time to wait for a page load to complete before throwing an error. If the timeout is null, page loads can be indefinite.
         * 
         * @param duration
         *            The amount duration to wait.
         * 
         * @return A self reference.
         */
        AugmentedWebDriver.Timeouts pageLoadTimeout(Duration duration);

        @Override
        AugmentedWebDriver.Timeouts pageLoadTimeout(long time, TimeUnit unit);

        /**
         * Retrieves the page load timeout.
         * 
         * @return The page load timeout. Null if infinity or unknown.
         */
        Duration getPageLoadTimeout();
        
        /**
         * Represents an immutable duration of timeout time.
         * 
         * @author Leonardo Carmona da Silva
         *         <ul>
         *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
         *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
         *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
         *         </ul>
         *
         * @since 1.0.1
         *
         */
        @Data
        public class Duration {

            private final long     time;
            private final TimeUnit unit;

            public Duration(long time, @NonNull TimeUnit unit) {
                this.time = time;
                this.unit = unit;
            }

            /**
             * Converts this duration to the given unit of time.
             *
             * @param unit
             *            The time unit to convert to.
             * 
             * @return The value of this duration in the specified unit of time.
             */
            public long in(TimeUnit unit) {
                return unit.convert(time, this.unit);
            }

        }

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
        
        /**
         * Switch the focus of future commands for this driver to the window index.
         *            
         * @param index
         *            Tab index.
         *            
         * @return This driver focused on the given window.
         */
        AugmentedWebDriver window(int index);

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
