package com.github.qacore.seleniumtestingtoolbox.annotations.webdrivers;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.function.Supplier;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.DefaultSafariDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.capabilities.DefaultSafariCapabilities;

/**
 * This annotation indicates to use the {@link SafariDriver}.
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
public @interface Safari {

    /**
     * The factory responsible for creating the {@link SafariDriver}.
     * 
     * @return The {@link WebDriverFactory}.
     */
    Class<? extends WebDriverFactory<? extends SafariDriver>> factory() default DefaultSafariDriverFactory.class;

    /**
     * The {@link Capabilities} {@link Supplier} injected in {@code factory()}.
     * 
     * @return The {@link Capabilities}.
     */
    Class<? extends Supplier<? extends Capabilities>> capabilities() default DefaultSafariCapabilities.class;
    
}
