package com.github.qacore.seleniumtestingtoolbox.html5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.internal.WrapsDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverContext;

import lombok.ToString;

/**
 * Represents the local storage and session storage.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see WebStorage
 * @see JSLocalStorage
 * @see JSSessionStorage
 *
 * @since 1.0.0
 *
 */
@ToString
public class JSWebStorage extends WebDriverContext implements WebStorage {

    private LocalStorage   localStorage   = new JSLocalStorage(this);
    private SessionStorage sessionStorage = new JSSessionStorage(this);

    public JSWebStorage(WrapsDriver driverContext) {
        super(driverContext);
    }

    public JSWebStorage(WebDriver webDriver) {
        super(webDriver);
    }

    public JSWebStorage() {
        super();
    }

    @Override
    public LocalStorage getLocalStorage() {
        return localStorage;
    }

    @Override
    public SessionStorage getSessionStorage() {
        return sessionStorage;
    }

}
