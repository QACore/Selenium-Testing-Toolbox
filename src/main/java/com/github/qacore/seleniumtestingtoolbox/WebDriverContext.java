package com.github.qacore.seleniumtestingtoolbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;

import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebDriver;
import com.github.qacore.seleniumtestingtoolbox.webdriver.DefaultAugmentedWebDriver;
import com.github.qacore.seleniumtestingtoolbox.webdriver.internal.AugmentedWrapsDriver;

import lombok.ToString;

/**
 * Managed {@link WebDriver} parallel context.
 * <p>
 * The default context is {@link WebDriverManager#getDriverContext()}.
 * </p>
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see WebDriverManager
 *
 * @since 1.0.0
 *
 */
@ToString
public class WebDriverContext implements AugmentedWrapsDriver {

    private AugmentedWrapsDriver driverContext;

    public WebDriverContext(WrapsDriver driverContext) {
        if (driverContext == null) {
            this.driverContext = WebDriverManager.getDriverContext();
        } else {
            if (driverContext instanceof AugmentedWrapsDriver) {
                this.driverContext = (AugmentedWrapsDriver) driverContext;
            } else {
                this.driverContext = new AugmentedWrapsDriver() {

                    @Override
                    public AugmentedWebDriver getWrappedDriver() {
                        return new DefaultAugmentedWebDriver(driverContext.getWrappedDriver());
                    }

                    @Override
                    public String toString() {
                        return this.getWrappedDriver().toString();
                    }

                };
            }
        }
    }

    public WebDriverContext(WebDriver webDriver) {
        if (webDriver == null) {
            this.driverContext = WebDriverManager.getDriverContext();
        } else {
            AugmentedWebDriver augmentedWebDriver;

            if (webDriver instanceof AugmentedWebDriver) {
                augmentedWebDriver = (AugmentedWebDriver) webDriver;
            } else {
                augmentedWebDriver = new DefaultAugmentedWebDriver(webDriver);
            }

            this.driverContext = new AugmentedWrapsDriver() {

                @Override
                public AugmentedWebDriver getWrappedDriver() {
                    return augmentedWebDriver;
                }

                @Override
                public String toString() {
                    return webDriver.toString();
                }

            };
        }
    }

    public WebDriverContext() {
        this.driverContext = WebDriverManager.getDriverContext();
    }

    @Override
    public AugmentedWebDriver getWrappedDriver() {
        return driverContext.getWrappedDriver();
    }

    public boolean isManaged() {
        return WebDriverManager.getDriverContext().equals(this.getDriverContext());
    }

    protected WrapsDriver getDriverContext() {
        return driverContext;
    }

    protected void setDriverContext(AugmentedWrapsDriver driverContext) {
        this.driverContext = driverContext;
    }

}
