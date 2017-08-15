package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverContext;

import lombok.ToString;

/**
 * Default parallel element locator factory.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 * 
 * @see WebDriverContext
 * @see ParallelElementLocatorFactory
 * 
 * @since 1.0.0
 *
 */
@ToString
public class DefaultSeleniumElementLocatorFactory extends WebDriverContext implements ParallelElementLocatorFactory {

    public DefaultSeleniumElementLocatorFactory(WrapsDriver driverContext) {
        super(driverContext);
    }

    public DefaultSeleniumElementLocatorFactory(WebDriver webDriver) {
        super(webDriver);
    }

    public DefaultSeleniumElementLocatorFactory() {
        super();
    }

    @Override
    public ParallelElementLocator createLocator(Field field) {
        return new DefaultSeleniumElementLocator(this, field);
    }

}
