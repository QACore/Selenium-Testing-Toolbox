package com.github.qacore.seleniumtestingtoolbox.webdriver.html5;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.AppCacheStatus;
import org.openqa.selenium.html5.ApplicationCache;
import org.openqa.selenium.internal.WrapsDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverContext;

import lombok.ToString;

/**
 * Represents the application cache status.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see ApplicationCache
 *
 * @since 1.0.0
 * 
 */
@ToString
public class JSApplicationCache extends WebDriverContext implements ApplicationCache {

    public JSApplicationCache(WrapsDriver driverContext) {
        super(driverContext);
    }

    public JSApplicationCache(WebDriver webDriver) {
        super(webDriver);
    }

    public JSApplicationCache() {
        super();
    }

    @Override
    public AppCacheStatus getStatus() {
        return AppCacheStatus.getEnum((int) (long) ((JavascriptExecutor) this.getWrappedDriver()).executeScript("return applicationCache.status"));
    }

}
