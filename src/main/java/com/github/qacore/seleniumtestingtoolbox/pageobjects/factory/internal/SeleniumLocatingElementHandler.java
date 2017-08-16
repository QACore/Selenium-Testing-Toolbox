package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import lombok.Data;

/**
 * Parallel locating element handler.
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
public class SeleniumLocatingElementHandler implements InvocationHandler {

    private ElementLocator elementLocator;

    public SeleniumLocatingElementHandler(ElementLocator elementLocator) {
        this.elementLocator = elementLocator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        WebElement element;

        try {
            element = elementLocator.findElement();
        } catch (NoSuchElementException e) {
            if ("toString".equals(method.getName())) {
                return "Proxy element for: " + elementLocator.toString();
            }

            throw e;
        }

        if ("getWrappedElement".equals(method.getName())) {
            if (element instanceof WrapsElement) {
                return ((WrapsElement) element).getWrappedElement();
            }

            return element;
        }

        try {
            return method.invoke(element, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

}
