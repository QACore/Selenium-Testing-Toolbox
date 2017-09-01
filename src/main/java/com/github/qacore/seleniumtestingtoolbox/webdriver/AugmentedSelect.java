package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * AugmentedSelect interface makes a protocol for all kind of select elements (standard html and custom model).
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
 * @since 1.0.1
 *
 */
public interface AugmentedSelect<T extends WebElement> {

    /**
     * @return Whether this select element support selecting multiple options at the same time? This is done by checking the value of the "multiple" attribute.
     */
    boolean isMultiple();

    /**
     * @return All options belonging to this select tag
     */
    List<T> getOptions();

    /**
     * @return All selected options belonging to this select tag
     */
    List<T> getAllSelectedOptions();

    /**
     * @return The first selected option in this select tag (or the currently selected option in a normal select)
     */
    T getFirstSelectedOption();

    /**
     * Select all options that display text matching the argument. That is, when given "Bar" this would select an option like:
     *
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param text
     *            The visible text to match against
     */
    void selectByVisibleText(String text);

    /**
     * Select the option at the given index. This is done by examining the "index" attribute of an element, and not merely by counting.
     *
     * @param index
     *            The option at this index will be selected
     */
    void selectByIndex(int index);

    /**
     * Select all options that have a value matching the argument. That is, when given "foo" this would select an option like:
     *
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param value
     *            The value to match against
     */
    void selectByValue(String value);

    /**
     * Clear all selected entries. This is only valid when the SELECT supports multiple selections.
     */
    void deselectAll();

    /**
     * Deselect all options that have a value matching the argument. That is, when given "foo" this would deselect an option like:
     *
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param value
     *            The value to match against
     */
    void deselectByValue(String value);

    /**
     * Deselect the option at the given index. This is done by examining the "index" attribute of an element, and not merely by counting.
     *
     * @param index
     *            The option at this index will be deselected
     */
    void deselectByIndex(int index);

    /**
     * Deselect all options that display text matching the argument. That is, when given "Bar" this would deselect an option like:
     *
     * &lt;option value="foo"&gt;Bar&lt;/option&gt;
     *
     * @param text
     *            The visible text to match against
     */
    void deselectByVisibleText(String text);

}
