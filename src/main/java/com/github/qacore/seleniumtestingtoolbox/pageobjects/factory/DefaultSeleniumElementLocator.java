package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.github.qacore.seleniumtestingtoolbox.WebPageFactory;
import com.github.qacore.seleniumtestingtoolbox.WebDriverContext;
import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebDriver;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The default selenium element locator, which will lazily locate an element or an element list on a page. This class is designed for use with the {@link WebPageFactory} and {@link PageFactory}. This class understands the annotations {@link FindBy}, {@link FindBys} and {@link FindAll}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 * 
 * @see WebDriverContext
 * @see ElementLocator
 *
 * @since 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DefaultSeleniumElementLocator extends WebDriverContext implements ElementLocator {

    private final Field  field;
    private final By     locator;
    private final String name;

    public DefaultSeleniumElementLocator(WrapsDriver driverContext, Field field) {
        super(driverContext);

        AugmentedElementAnnotations annotations = new AugmentedElementAnnotations(field);

        this.field = field;
        this.locator = annotations.buildBy();
        this.name = annotations.getName();
    }

    public DefaultSeleniumElementLocator(WebDriver webDriver, Field field) {
        super(webDriver);

        AugmentedElementAnnotations annotations = new AugmentedElementAnnotations(field);

        this.field = field;
        this.locator = annotations.buildBy();
        this.name = annotations.getName();
    }

    public DefaultSeleniumElementLocator(Field field) {
        super();

        AugmentedElementAnnotations annotations = new AugmentedElementAnnotations(field);

        this.field = field;
        this.locator = annotations.buildBy();
        this.name = annotations.getName();
    }

    @Override
    public WebElement findElement() {
        return this.getWrappedDriver().findElement(this.getLocator(), this.getName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WebElement> findElements() {
        return (List<WebElement>) (List<?>) this.getWrappedDriver().findElements(this.getLocator(), this.getName());
    }

    @Override
    public AugmentedWebDriver getWrappedDriver() {
        AugmentedWebDriver driver = super.getWrappedDriver();

        if (driver == null) {
            throw new WebDriverException("WebDriver is null. Please, use WebDriverManager.setDriver(WebDriver driver)!");
        }

        return driver;
    }

}
