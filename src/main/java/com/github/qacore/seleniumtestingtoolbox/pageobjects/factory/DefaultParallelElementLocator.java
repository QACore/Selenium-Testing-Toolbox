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
import org.openqa.selenium.support.pagefactory.Annotations;

import com.github.qacore.seleniumtestingtoolbox.WebDriverContext;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.ParallelPageFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The default parallel element locator, which will lazily locate an element or an element list on a page. This class is designed for use with the {@link ParallelPageFactory} and {@link PageFactory}. This class understands the annotations {@link FindBy}, {@link FindBys} and {@link FindAll}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 * 
 * @see WebDriverContext
 * @see ParallelElementLocator
 *
 * @since 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
// @Getter
public class DefaultParallelElementLocator extends WebDriverContext implements ParallelElementLocator {

    private final Field field;
    private final By    locator;

    public DefaultParallelElementLocator(WrapsDriver driverContext, Field field) {
        super(driverContext);

        this.field = field;
        this.locator = new Annotations(field).buildBy();
    }

    public DefaultParallelElementLocator(WebDriver webDriver, Field field) {
        super(webDriver);

        this.field = field;
        this.locator = new Annotations(field).buildBy();
    }

    public DefaultParallelElementLocator(Field field) {
        super();

        this.field = field;
        this.locator = new Annotations(field).buildBy();
    }

    @Override
    public WebElement findElement() {
        return this.getWrappedDriver().findElement(this.getLocator());
    }

    @Override
    public List<WebElement> findElements() {
        return this.getWrappedDriver().findElements(this.getLocator());
    }

    @Override
    public WebDriver getWrappedDriver() {
        WebDriver driver = super.getWrappedDriver();

        if (driver == null) {
            throw new WebDriverException("WebDriver is null. Please, use WebDriverManager.setDriver(WebDriver driver)!");
        }

        return driver;
    }

}