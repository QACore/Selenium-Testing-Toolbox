package com.github.qacore.seleniumtestingtoolbox;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebDriver;
import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebElement;
import com.github.qacore.seleniumtestingtoolbox.webdriver.DefaultAugmentedWebDriver;
import com.github.qacore.seleniumtestingtoolbox.webdriver.DefaultAugmentedWebElement;
import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsRegistry;

/**
 * {@link WebDriver} factory.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 * 
 * @param <T>
 *            {@link WebDriver} type.
 * 
 * @since 1.0.0
 *
 */
public interface WebDriverFactory<T extends WebDriver> {

    /**
     * Augment an {@link WebDriver}.
     * 
     * @param driver
     *            The {@link WebDriver}.
     * 
     * @return An augmented {@link WebDriver}.
     */
    public static AugmentedWebDriver augment(WebDriver driver) {
        if (driver instanceof AugmentedWebDriver) {
            return (AugmentedWebDriver) driver;
        }

        return new DefaultAugmentedWebDriver(driver);
    }

    /**
     * Augment an {@link WebElement}.
     * 
     * @param element
     *            The {@link WebElement}.
     * 
     * @param name
     *            The {@link WebElement} name. Can be null.
     * 
     * @param eventsRegistry
     *            The {@link EventsRegistry}. Can be null.
     * 
     * @return An augmented {@link WebElement}.
     */
    public static AugmentedWebElement augment(WebElement element, String name, EventsRegistry eventsRegistry) {
        if (element instanceof AugmentedWebElement) {
            return (AugmentedWebElement) element;
        }

        return new DefaultAugmentedWebElement(element, name, eventsRegistry);
    }

    /**
     * Creates a new instance of {@link WebDriver}.
     * 
     * @param capabilities
     *            Desired capabilities for the {@link WebDriver}.
     * 
     * @return New instance of {@link WebDriver}.
     */
    T newWebDriver(Capabilities capabilities);

}
