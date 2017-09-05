package com.github.qacore.seleniumtestingtoolbox.annotations.webdrivers;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.DefaultHtmlUnitDriverFactory;

/**
 * This annotation indicates to use the {@link HtmlUnitDriver}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see WebDriverFactory
 *
 * @since 1.0.2
 *
 */
@Documented
@Retention(RUNTIME)
public @interface HtmlUnit {

    /**
     * The factory responsible for creating the {@link HtmlUnitDriver}.
     * 
     * @return The {@link WebDriverFactory}.
     */
    Class<? extends WebDriverFactory<? extends HtmlUnitDriver>> factory() default DefaultHtmlUnitDriverFactory.class;

}
