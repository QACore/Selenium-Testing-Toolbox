package com.github.qacore.seleniumtestingtoolbox;

import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.PageFactory;

import com.github.qacore.seleniumtestingtoolbox.pageobjects.AbstractPage;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.DefaultSeleniumElementLocatorFactory;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.DefaultSeleniumFieldDecorator;

/**
 * Web Page Factory class to make using Page Objects simpler and easier.
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
public final class WebPageFactory {

    public static void initElements(AbstractPage<?> page) {
        PageFactory.initElements(new DefaultSeleniumFieldDecorator(new DefaultSeleniumElementLocatorFactory(page)), page);
    }

    public static void initElements(WrapsDriver page) {
        PageFactory.initElements(new DefaultSeleniumFieldDecorator(new DefaultSeleniumElementLocatorFactory(page)), page);
    }

    public static void initElements(Object page) {
        PageFactory.initElements(new DefaultSeleniumFieldDecorator(new DefaultSeleniumElementLocatorFactory()), page);
    }

    private WebPageFactory() {

    }

}
