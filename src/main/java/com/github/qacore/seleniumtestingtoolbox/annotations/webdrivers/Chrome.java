package com.github.qacore.seleniumtestingtoolbox.annotations.webdrivers;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.function.Supplier;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.DefaultChromeDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.capabilities.DefaultChromeCapabilities;

/**
 * This annotation indicates to use the {@link ChromeDriver}.
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
public @interface Chrome {

    /**
     * The factory responsible for creating the {@link ChromeDriver}.
     * 
     * @return The {@link WebDriverFactory}.
     */
    Class<? extends WebDriverFactory<? extends ChromeDriver>> factory() default DefaultChromeDriverFactory.class;

    /**
     * The {@link Capabilities} {@link Supplier} injected in {@code factory()}.
     * 
     * @return The {@link Capabilities}.
     */
    Class<? extends Supplier<? extends Capabilities>> capabilities() default DefaultChromeCapabilities.class;
    
}
