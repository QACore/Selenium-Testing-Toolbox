package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.util.List;

import org.openqa.selenium.By;
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
 *            Element type.
 *
 * @since 1.0.0
 *
 */
public interface AugmentedSearchContext<T extends WebElement> {

    List<T> findElements(By by, String name);

    T findElement(By by, String name);

}
