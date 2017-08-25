package com.github.qacore.seleniumtestingtoolbox.webdriver;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * 
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @since 1.0.0
 *
 */
public interface AugmentedWebElement extends WebElement, AugmentedSearchContext<AugmentedWebElement>, Locatable, WrapsElement {

    /**
     * Clicks (without releasing) in the middle of the given element. This is equivalent to: <i>Actions.moveToElement(onElement).clickAndHold()</i>
     */
    void clickAndHold();

    /**
     * Performs a context-click at middle of the given element. First performs a mouseMove to the location of the element.
     */
    void contextClick();

    /**
     * Performs a double-click at middle of the given element. Equivalent to: <i>Actions.moveToElement(element).doubleClick()</i>
     */
    void doubleClick();

    /**
     * A convenience method that performs click-and-hold at the location of the source element, moves to the location of the target element, then releases the mouse.
     * 
     * @param target
     *            element to move to and release the mouse at.
     */
    void dragAndDrop(WebElement target);

    /**
     * A convenience method that performs click-and-hold at the location of the source element, moves by a given offset, then releases the mouse.
     * 
     * @param xOffset
     *            horizontal move offset.
     * 
     * @param yOffset
     *            vertical move offset.
     */
    void dragAndDropBy(int xOffset, int yOffset);

    /**
     * Performs a modifier key press. Does not release the modifier key - subsequent interactions may assume it's kept pressed. Note that the modifier key is <b>never</b> released implicitly - either <i>keyUp(theKey)</i> or <i>sendKeys(Keys.NULL)</i> must be called to release the modifier.
     * 
     * @param key
     *            Either {@link Keys#SHIFT}, {@link Keys#ALT} or {@link Keys#CONTROL}. If the provided key is none of those, {@link IllegalArgumentException} is thrown.
     */
    void keyDown(CharSequence key);

    /**
     * Performs a modifier key release. Releasing a non-depressed modifier key will yield undefined behaviour.
     *
     * @param key
     *            Either {@link Keys#SHIFT}, {@link Keys#ALT} or {@link Keys#CONTROL}.
     */
    void keyUp(CharSequence key);

    /**
     * Clicks (without releasing) in the middle of the given element. This is equivalent to: <i>Actions.moveToElement(onElement).clickAndHold()</i>
     */
    void moveToElement();

    /**
     * Moves the mouse to an offset from the top-left corner of the element. The element is scrolled into view and its location is calculated using getBoundingClientRect.
     * 
     * @param xOffset
     *            Offset from the top-left corner. A negative value means coordinates left from the element.
     * 
     * @param yOffset
     *            Offset from the top-left corner. A negative value means coordinates above the element.
     * 
     */
    void moveToElement(int xOffset, int yOffset);

    /**
     * Releases the depressed left mouse button, in the middle of the given element. This is equivalent to: <i>Actions.moveToElement(onElement).release()</i>
     *
     * Invoking this action without invoking {@link #clickAndHold()} first will result in undefined behaviour.
     */
    void release();

    /**
     * @return The {@link WebDriver} name.
     */
    String name();

}
