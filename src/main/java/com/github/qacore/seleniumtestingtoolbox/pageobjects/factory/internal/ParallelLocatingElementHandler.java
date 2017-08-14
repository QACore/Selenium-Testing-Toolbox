package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.ParallelElementLocator;

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
public class ParallelLocatingElementHandler implements InvocationHandler {

    private ParallelElementLocator parallelElementLocator;

    public ParallelLocatingElementHandler(ParallelElementLocator parallelElementLocator) {
        this.parallelElementLocator = parallelElementLocator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        WebElement element;

        try {
            element = parallelElementLocator.findElement();
        } catch (NoSuchElementException e) {
            if ("toString".equals(method.getName())) {
                return "Proxy element for: " + parallelElementLocator.toString();
            }

            throw e;
        }

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }

        try {
            return method.invoke(element, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

}
