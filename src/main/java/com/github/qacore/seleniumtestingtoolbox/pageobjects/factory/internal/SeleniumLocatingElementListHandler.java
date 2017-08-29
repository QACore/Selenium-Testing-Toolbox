package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.support.pagefactory.ElementLocator;

import lombok.Data;

/**
 * Parallel locating element list handler.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see InvocationHandler
 *
 * @since 1.0.0
 *
 */
@Data
public class SeleniumLocatingElementListHandler implements InvocationHandler {

    private ElementLocator elementLocator;

    public SeleniumLocatingElementListHandler(ElementLocator elementLocator) {
        this.elementLocator = elementLocator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(elementLocator.findElements(), args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

}
