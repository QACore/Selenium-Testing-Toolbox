package com.github.qacore.seleniumtestingtoolbox.webdriver.events;

import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * Use this class as base class, if you want to implement a {@link AugmentedWebDriverEventListener} and are only interested in some events. All methods provided by this class have an empty method body.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 */
public abstract class AbstractAugmentedWebDriverEventListener extends AbstractWebDriverEventListener implements AugmentedWebDriverEventListener {

}
