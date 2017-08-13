package com.github.qacore.seleniumtestingtoolbox.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.security.Credentials;

import com.github.qacore.seleniumtestingtoolbox.WebDriverContext;

/**
 * This abstract class indicates that the classes executes the basic actions of the <b>Login Page Object</b> pattern.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @param <I>
 *            Itself class for fluent pattern.
 *
 * @since 1.0.0
 * 
 * @see AbstractPage
 * @see WebDriverContext
 *
 */
public abstract class AbstractLoginPage<I extends AbstractLoginPage<I>> extends AbstractPage<I> implements Credentials {

    public AbstractLoginPage(WrapsDriver driverContext) {
        super(driverContext);
    }

    public AbstractLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public AbstractLoginPage() {
        super();
    }

    /**
     * Performs an authentication on the page.
     * 
     * @param credentials
     *            The authentication credentials for a user.
     * 
     * @return Itself.
     */
    public abstract I loginAs(Credentials credentials);

    /**
     * Performs an authentication on the page.
     * 
     * @return Itself.
     */
    public abstract I logout();

    /**
     * Indicates if you are logged in.
     * 
     * @param credentials
     *            The authentication credentials for a user.
     * 
     * @return Itself.
     */
    public abstract boolean isLogged(Credentials credentials);

}
