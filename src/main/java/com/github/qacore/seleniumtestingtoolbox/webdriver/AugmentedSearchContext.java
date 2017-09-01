package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Augmented {@link SearchContext}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @param <T>
 *            The {@link WebElement} type.
 *
 * @since 1.0.0
 *
 */
public interface AugmentedSearchContext<T extends WebElement> {

    /**
     * Find all elements within the current context using the given mechanism.
     *
     * @param by
     *            The locating mechanism to use.
     * 
     * @param name
     *            The name of the elements.
     * 
     * @return A list of all {@link WebElement WebElements}, or an empty list if nothing matches.
     * 
     * @see By
     */
    List<T> findElements(By by, String name);

    /**
     * Find the first {@link WebElement} using the given method.
     *
     * @param by
     *            The locating mechanism.
     * 
     * @param name
     *            The name of the element.
     * 
     * @return The first matching element on the current context.
     * 
     * @throws NoSuchElementException
     *             If no matching elements are found.
     * 
     * @see By
     */
    T findElement(By by, String name);

}
