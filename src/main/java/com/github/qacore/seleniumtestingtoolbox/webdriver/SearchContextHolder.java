package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsRegistry;

/**
 * Search context holder to prevent duplicate codes.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @since 1.0.1
 *
 */
class SearchContextHolder {

    /**
     * Find a list of {@link AugmentedWebElement}.
     * 
     * @param searchContext
     *            The {@link SearchContext}.
     * 
     * @param by
     *            The locating mechanism to use.
     * 
     * @param name
     *            The name of the elements.
     * 
     * @param events
     *            The {@link EventsRegistry}.
     * 
     * @return A list of {@link AugmentedWebElement}.
     */
    public static List<AugmentedWebElement> findElements(SearchContext searchContext, By by, String name, EventsRegistry events) {
        List<WebElement> elements = searchContext.findElements(by);
        AugmentedWebElement[] augmentedElements = new AugmentedWebElement[elements.size()];

        if (name == null) {
            for (int i = 0; i < augmentedElements.length; i++) {
                augmentedElements[i] = new DefaultAugmentedWebElement(elements.get(i), null, events);
            }
        } else {
            for (int i = 0; i < augmentedElements.length; i++) {
                augmentedElements[i] = new DefaultAugmentedWebElement(elements.get(i), name + " [" + i + "]", events);
            }
        }

        return Arrays.asList(augmentedElements);
    }

    private SearchContextHolder() {

    }

}
