package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Augmented {@link WebElement}.
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
public interface AugmentedWebElement extends WebElement, AugmentedSearchContext<AugmentedWebElement>, AugmentedSelect<AugmentedWebElement>, Locatable, WrapsElement {

    /**
     * Is the element currently present or not?
     * 
     * @return True if the element is present, false otherwise.
     */
    boolean isPresent();

    /**
     * Is the element currently clicable or not?
     * 
     * @return True if the element is clicable, false otherwise.
     */
    boolean isClicable();

    /**
     * Open the link in new tab.
     */
    void openLinkInNewTab();

    /**
     * Open the link. Synonym for {@link WebElement#click()}.
     */
    void openLink();

    /**
     * Checks this checkbox if not checked already.
     */
    void check();

    /**
     * Unchecks this checkbox if not unchecked already.
     */
    void uncheck();

    /**
     * Indeterminates this checkbox if not indeterminated already.
     */
    void indeterminate();

    /**
     * Download the image to specified path.
     * 
     * @param pathname
     *            A pathname string.
     * 
     * @param connectionTimeout
     *            The number of milliseconds until this method will timeout if no connection could be established to the <code>source</code>.
     * 
     * @param readTimeout
     *            The number of milliseconds until this method will timeout if no data could be read from the <code>source</code>.
     * 
     * @return The file.
     */
    public File downloadImage(String pathname, int connectionTimeout, int readTimeout);

    /**
     * Download the image to specified path.
     * 
     * @param pathname
     *            A pathname string.
     * 
     * @return The file.
     */
    public File downloadImage(String pathname);

    /**
     * Returns if this checkbox is checked or not.
     * 
     * @return True if is checked. Otherwise false.
     */
    boolean isChecked();

    /**
     * Returns if this checkbox is indeterminate or not.
     * 
     * @return True if is indeterminated. Otherwise false.
     */
    boolean isIndeterminate();

    /**
     * Returns whether this checkbox is checked when the page loads.
     * 
     * @return True if this checkbox is checked when the page loads. Otherwise false.
     */
    boolean isDefaultChecked();

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
     * @return The {@link AugmentedWebElement WebElement} attributes.
     */
    Attributes attributes();

    /**
     * @return The {@link AugmentedWebElement WebElement} axes.
     */
    Axes axes();

    /**
     * @return The {@link WebDriver} name.
     */
    String name();

    /**
     * {@link AugmentedWebElement} attributes.
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
    interface Attributes {

        /**
         * Retrieves the id of this element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("id")}.
         * 
         * @return The id of this element.
         */
        String id();

        /**
         * Retrieves the name of this element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("name")}.
         * 
         * @return The name of this element.
         */
        String name();

        /**
         * Retrieves the style class of this element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("class")}.
         * 
         * @return The style class of this element.
         */
        List<String> styleClass();

        /**
         * Retrieves the title of this element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("title")}.
         * 
         * @return The title of this element.
         */
        String title();

        /**
         * Retrieves that a button should automatically get focus when the page loads. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("autofocus")}.
         * 
         * @return True if autofocus is present. Otherwise, false.
         */
        boolean autoFocus();

        /**
         * Retrieves the form attribute. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("form")}.
         * 
         * @return The form attribute.
         */
        String form();

        /**
         * Retrieves the formaction attribute. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("formaction")}.
         * 
         * @return The formaction attribute.
         */
        URL formAction();

        /**
         * Retrieves the formenctype attribute. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("formenctype")}.
         * 
         * @return The formenctype attribute.
         */
        FormEncType formEncType();

        /**
         * Retrieves the formmethod attribute. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("formmethod")}.
         * 
         * @return The formmethod attribute.
         */
        String formMethod();

        /**
         * Retrieves if the form-data should not be validated on submission. Only for type="submit". Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("formnovalidate")}.
         * 
         * @return True if formnovalidate is present. Otherwise, false.
         */
        boolean formNoValidate();

        /**
         * Retrieves the formtarget attribute. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("formtarget")}.
         * 
         * @return The formtarget attribute.
         */
        String formTarget();

        /**
         * Retrieves the type attribute. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("type")}.
         * 
         * @return The type attribute.
         */
        ButtonType type();

        /**
         * Retrieves the value attribute. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("value")}.
         * 
         * @return The value attribute.
         */
        String value();

        /**
         * Retrieves this checkbox default value. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("defaultValue")}.
         * 
         * @return The checkbox default value.
         */
        public String defaultValue();

        /**
         * Returns if this checkbox is checked or not. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("checked")}.
         * 
         * @return True if is checked. Otherwise false.
         */
        boolean checked();

        /**
         * Returns if this checkbox is indeterminate or not. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("indeterminate")}.
         * 
         * @return True if is indeterminated. Otherwise false.
         */
        boolean indeterminate();

        /**
         * Returns whether this checkbox is checked when the page loads. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("defaultChecked")}.
         * 
         * @return True if this checkbox is checked when the page loads. Otherwise false.
         */
        boolean defaultChecked();

        /**
         * Retrieves the specified URL of the page the link goes to. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("href")}.
         * 
         * @return The href of this link.
         */
        String href();

        /**
         * Retrieves the specification of where to open the linked document. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("target")}.
         * 
         * @return The target of this link.
         */
        String target();

        /**
         * Retrieves the alt of this image. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("alt")}.
         * 
         * @return The alt of this image.
         */
        public String alt();

        /**
         * Retrieves the src of this image. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("src")}.
         * 
         * @return The src of this image.
         */
        public String src();

        /**
         * Retrieves the form element id this label is bound to. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("for")}.
         * 
         * @return The for attribute of this label.
         */
        public String getFor();

        /**
         * Retrieves the size of this web element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("autocomplete")}.
         * 
         * @return True if this web element is auto complete. Otherwise false.
         */
        boolean autoComplete();

        /**
         * Retrieves the size of this web element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("maxlength")}.
         * 
         * @return The length of this web element.
         */
        Integer maxLength();

        /**
         * Retrieves the size of this web element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("pattern")}.
         * 
         * @return The pattern of this web element.
         */
        String pattern();

        /**
         * Retrieves the size of this web element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("placeholder")}.
         * 
         * @return The placeholder of this web element.
         */
        String placeholder();

        /**
         * Retrieves the size of this web element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("readonly")}.
         * 
         * @return True if this web element is read only. Otherwise false.
         */
        boolean readOnly();

        /**
         * Retrieves the size of this web element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("required")}.
         * 
         * @return True if this web element is required. Otherwise false.
         */
        boolean required();

        /**
         * Retrieves the size of this web element. Synonym for {@link WebElement#getAttribute(String) WebElement.getAttribute("size")}.
         * 
         * @return The size of this web element.
         */
        int size();

    }

    /**
     * Handles XPath axes for an {@link WebElement}. (<a href='https://www.w3.org/TR/xpath/#axes'>https://www.w3.org/TR/xpath/#axes</a>)
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
    interface Axes {

        /**
         * Find parent {@link AugmentedWebElement WebElement}. Synonym for {@link WebElement#findElement(By) WebElement.findElement(By.xpath("parent::*"))}.
         * 
         * @return A {@link AugmentedWebElement WebElement}.
         */
        AugmentedWebElement parent();

        /**
         * Handle an XPath axe. Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath(axe + "::" + node"))}.
         * 
         * @param axe
         * 
         * @param node
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        List<AugmentedWebElement> handleAxe(String axe, String node);

        /**
         * Handle an XPath axe. Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath("axe::*"))}.
         * 
         * @param axe
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        default List<AugmentedWebElement> handleAxe(String axe) {
            return handleAxe(axe, "*");
        }

        /**
         * Find ancestor {@link AugmentedWebElement WebElements}. Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath("ancestor::*"))}.
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        List<AugmentedWebElement> ancestors();

        /**
         * Find descendants {@link AugmentedWebElement WebElements} (children, grandchildren etc). Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath("descendant::*"))}. 
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        List<AugmentedWebElement> descendants();

        /**
         * Find following {@link AugmentedWebElement WebElements}. Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath("following::*"))}.
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        List<AugmentedWebElement> followings();

        /**
         * Find following sibling {@link AugmentedWebElement WebElements}. Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath("following-sibling::*"))}.
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        List<AugmentedWebElement> followingSiblings();

        /**
         * Find preceding {@link AugmentedWebElement WebElements} (Ancestors are NOT included). Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath("preceding::*"))}.
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        List<AugmentedWebElement> precedings();

        /**
         * Find preceding sibling {@link AugmentedWebElement WebElements}. Synonym for {@link WebElement#findElements(By) WebElement.findElements(By.xpath("preceding-sibling::*"))}.
         * 
         * @return A {@link List} of {@link AugmentedWebElement WebElement}.
         */
        List<AugmentedWebElement> precedingSiblings();

    }

    /**
     * The formenctype attribute specifies how form-data should be encoded before sending it to a server. This attribute overrides the form's enctype attribute.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     * 
     * @see <a href="https://www.w3.org/TR/2011/WD-html5-20110525/association-of-controls-and-forms.html#attr-fs-formenctype">https://www.w3.org/TR/2011/WD-html5-20110525/association-of-controls-and-forms.html#attr-fs-formenctype</a>
     *
     * @since 1.0.1
     *
     */
    enum FormEncType {

        /**
         * Default. All characters will be encoded before sent.
         */
        APPLICATION_X_WWW_FORM_ENCODED,

        /**
         * No characters are encoded (use this when you are using forms that have a file upload control).
         */
        MULTIPART_FORM_DATA,

        /**
         * Spaces are converted to "+" symbols, but no characters are encoded.
         */
        TEXT_PLAIN;

    }

    /**
     * Html button types.
     * 
     * @author Leonardo Carmona da Silva
     *         <ul>
     *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
     *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
     *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
     *         </ul>
     * 
     * @see <a href="https://www.w3.org/TR/2011/WD-html5-20110525/the-button-element.html#attr-button-type">https://www.w3.org/TR/2011/WD-html5-20110525/the-button-element.html#attr-button-type</a>
     * 
     * @since 1.0.1
     *
     */
    enum ButtonType {

        /**
         * The button is a clickable button.
         */
        BUTTON,

        /**
         * The button is a submit button (submits form-data).
         */
        SUBMIT,

        /**
         * The button is a reset button (resets the form-data to its initial values).
         */
        RESET;

    }

}
