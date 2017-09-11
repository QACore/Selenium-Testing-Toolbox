package com.github.qacore.seleniumtestingtoolbox.annotations.webdrivers;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.function.Supplier;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.opera.OperaDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.DefaultOperaDriverFactory;
import com.github.qacore.seleniumtestingtoolbox.webdriver.factory.capabilities.DefaultOperaDriverCapabilities;

/**
 * This annotation indicates to use the {@link OperaDriver}.
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
public @interface Opera {

    /**
     * The factory responsible for creating the {@link OperaDriver}.
     * 
     * @return The {@link WebDriverFactory}.
     */
    Class<? extends WebDriverFactory<? extends OperaDriver>> factory() default DefaultOperaDriverFactory.class;

    /**
     * The {@link Capabilities} {@link Supplier} injected in {@code factory()}.
     * 
     * @return The {@link Capabilities}.
     */
    Class<? extends Supplier<? extends Capabilities>> capabilities() default DefaultOperaDriverCapabilities.class;
    
}
