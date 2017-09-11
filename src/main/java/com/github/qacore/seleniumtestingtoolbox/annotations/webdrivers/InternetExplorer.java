package com.github.qacore.seleniumtestingtoolbox.annotations.webdrivers;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.function.Supplier;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.DefaultInternetExplorerDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.capabilities.DefaultInternetExplorerCapabilities;

/**
 * This annotation indicates to use the {@link InternetExplorerDriver}.
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
public @interface InternetExplorer {

    /**
     * The factory responsible for creating the {@link InternetExplorerDriver}.
     * 
     * @return The {@link WebDriverFactory}.
     */
    Class<? extends WebDriverFactory<? extends InternetExplorerDriver>> factory() default DefaultInternetExplorerDriverFactory.class;

    /**
     * The {@link Capabilities} {@link Supplier} injected in {@code factory()}.
     * 
     * @return The {@link Capabilities}.
     */
    Class<? extends Supplier<? extends Capabilities>> capabilities() default DefaultInternetExplorerCapabilities.class;
    
}
