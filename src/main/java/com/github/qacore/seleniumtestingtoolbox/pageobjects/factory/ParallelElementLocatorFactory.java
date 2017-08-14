package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory;

import java.lang.reflect.Field;

import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * A factory for producing {@link ParallelElementLocator}s. It is expected that a new ElementLocator will be returned per call linked to a driver context.
 * <p>
 * This factory must have support to parallel calls.
 * </p>
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see ElementLocatorFactory
 * @see WrapsDriver
 *
 * @since 1.0.0
 *
 */
public interface ParallelElementLocatorFactory extends ElementLocatorFactory, WrapsDriver {

    /**
     * When a field on a class needs to be decorated with an {@link ParallelElementLocator} this method will be called.
     *
     * @param field
     *            The field.
     * 
     * @return An ElementLocator object.
     */
    @Override
    ParallelElementLocator createLocator(Field field);

}
