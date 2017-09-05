package com.github.qacore.seleniumtestingtoolbox.webdriver.factory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverFactory;

/**
 * Default {@link RemoteWebDriver} factory
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
public class DefaultRemoteDriverFactory implements WebDriverFactory<RemoteWebDriver> {

    @Override
    public RemoteWebDriver newWebDriver(Capabilities capabilities) {
        return new RemoteWebDriver(capabilities);
    }

}
