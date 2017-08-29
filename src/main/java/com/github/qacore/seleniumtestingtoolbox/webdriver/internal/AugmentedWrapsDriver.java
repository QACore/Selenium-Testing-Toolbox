package com.github.qacore.seleniumtestingtoolbox.webdriver.internal;

import org.openqa.selenium.internal.WrapsDriver;

import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebDriver;

/**
 * Augmented {@link WrapsDriver}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see 1.0.0
 *
 */
public interface AugmentedWrapsDriver extends WrapsDriver {

    @Override
    AugmentedWebDriver getWrappedDriver();

}
