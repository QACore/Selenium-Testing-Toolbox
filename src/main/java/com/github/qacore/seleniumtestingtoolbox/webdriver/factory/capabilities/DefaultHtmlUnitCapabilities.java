package com.github.qacore.seleniumtestingtoolbox.webdriver.factory.capabilities;

import java.util.function.Supplier;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Default {@link HtmlUnitDriver} {@link Capabilities}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see Supplier
 *
 * @since 1.0.2
 *
 */
public class DefaultHtmlUnitCapabilities implements Supplier<Capabilities> {

    @Override
    public Capabilities get() {
        return DesiredCapabilities.htmlUnit();
    }

}
