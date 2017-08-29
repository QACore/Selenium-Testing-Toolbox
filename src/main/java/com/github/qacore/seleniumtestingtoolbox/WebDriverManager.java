package com.github.qacore.seleniumtestingtoolbox;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebDriver;
import com.github.qacore.seleniumtestingtoolbox.webdriver.DefaultAugmentedWebDriver;
import com.github.qacore.seleniumtestingtoolbox.webdriver.internal.AugmentedWrapsDriver;

/**
 * Manage {@link WebDriver}, one per {@link Thread} to support parallel testing.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 * 
 * @since 1.0.0
 * 
 */
public final class WebDriverManager {
    
    private static final ThreadLocal<AugmentedWebDriver> webDriver     = new ThreadLocal<>();
    private static final AugmentedWrapsDriver            driverContext = new AugmentedWrapsDriver() {
        
        @Override
        public AugmentedWebDriver getWrappedDriver() {            
            return webDriver.get();
        }
        
        @Override
        public String toString() {
            return "WebDriverManager(thread=" + Thread.currentThread().getName() + ", driver=" + webDriver.get() + ")";
        }
        
    };

    public static AugmentedWebDriver getDriver() {
        return webDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        if (driver instanceof AugmentedWebDriver) {
            webDriver.set((AugmentedWebDriver) driver);
        } else {
            webDriver.set(new DefaultAugmentedWebDriver(driver));
        }
    }

    public static AugmentedWrapsDriver getDriverContext() {
        return driverContext;
    }
    
    public static void quit() {
        WebDriver driver = webDriver.get();
        
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                webDriver.set(null);
            }
        }
    }

    public static boolean isOpened() {
        WebDriver driver = webDriver.get();

        if (driver == null) {
            return false;
        }

        try {
            return driver.getWindowHandles().size() > 0;
        } catch (NoSuchSessionException e) {
            return false;
        }
    }

    private WebDriverManager() {

    }

}
