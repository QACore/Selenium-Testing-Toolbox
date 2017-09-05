package com.github.qacore.seleniumtestingtoolbox.webdriver.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.opera.OperaDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverFactory;

/**
 * Default {@link OperaDriver} factory
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
public class DefaultOperaDriverFactory implements WebDriverFactory<OperaDriver> {

    @Override
    public OperaDriver newWebDriver(Capabilities capabilities) {
        if (capabilities == null) {
            return new OperaDriver();
        }

        return new OperaDriver(capabilities);
    }

}
