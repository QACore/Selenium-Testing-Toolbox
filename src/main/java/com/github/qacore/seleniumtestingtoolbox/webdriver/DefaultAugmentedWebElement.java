package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.Select;

import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsControl;
import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsRegistry;

import lombok.ToString;

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
public class DefaultAugmentedWebElement implements AugmentedWebElement, WrapsDriver, EventsControl {

    private WebElement     wrappedElement;
    private String         name;
    private EventsRegistry events;
    private Actions        actions;
    private WebDriver      wrappedDriver;

    private Attributes     attributes;
    private Axes           axes;

    public DefaultAugmentedWebElement(WebElement wrappedElement, String name, EventsRegistry events) {
        this.wrappedElement = wrappedElement;

        if (name == null) {
            this.name = wrappedElement.toString();
        } else {
            this.name = name;
        }

        if (events == null) {
            this.events = new EventsRegistry();
        } else {
            this.events = events;
        }

        if (wrappedElement instanceof WrapsDriver) {
            wrappedDriver = ((WrapsDriver) wrappedElement).getWrappedDriver();
            actions = new Actions(wrappedDriver);
        }

        this.attributes = new DefaultAttributes();
        this.axes = new DefaultAxes();
    }

    @Override
    public boolean isLoaded() {
        try {
            this.getWrappedElement().isEnabled();

            return true;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    @Override
    public boolean isClicable() {
        return this.isDisplayed() && this.isEnabled();
    }

    @Override
    public void openLinkInNewTab() {
        synchronized (this.getClass()) {
            WebDriver driver = this.getWrappedDriver();
            Set<String> initialTabs = driver.getWindowHandles();

            this.openLink();

            Set<String> tabs = driver.getWindowHandles();
            tabs.removeAll(initialTabs);

            driver.switchTo().window(tabs.iterator().next());
        }
    }

    @Override
    public void openLink() {
        this.getWrappedElement().click();
    }

    @Override
    public boolean isMultiple() {
        return new Select(this).isMultiple();
    }

    @Override
    public List<AugmentedWebElement> getOptions() {
        return new Select(this).getOptions().stream().map(w -> new DefaultAugmentedWebElement(w, w.getText(), this.events())).collect(Collectors.toList());
    }

    @Override
    public List<AugmentedWebElement> getAllSelectedOptions() {
        return new Select(this).getAllSelectedOptions().stream().map(w -> new DefaultAugmentedWebElement(w, w.getText(), this.events())).collect(Collectors.toList());
    }

    @Override
    public AugmentedWebElement getFirstSelectedOption() {
        WebElement element = new Select(this).getFirstSelectedOption();

        return new DefaultAugmentedWebElement(element, element.getText(), this.events());
    }

    @Override
    public void selectByVisibleText(String text) {
        new Select(this).selectByVisibleText(text);
    }

    @Override
    public void selectByIndex(int index) {
        new Select(this).selectByIndex(index);
    }

    @Override
    public void selectByValue(String value) {
        new Select(this).selectByValue(value);
    }

    @Override
    public void deselectAll() {
        new Select(this).deselectAll();
    }

    @Override
    public void deselectByValue(String value) {
        new Select(this).deselectByValue(value);
    }

    @Override
    public void deselectByIndex(int index) {
        new Select(this).deselectByIndex(index);
    }

    @Override
    public void deselectByVisibleText(String text) {
        new Select(this).deselectByVisibleText(text);
    }

    @Override
    public void check() {
        if (!this.isChecked()) {
            if (this.isClicable()) {
                this.click();
            } else {
                throw new ElementNotInteractableException("Element is not clicable");
            }
        }
    }

    @Override
    public void uncheck() {
        if (this.isChecked()) {
            if (this.isClicable()) {
                this.click();
            } else {
                throw new ElementNotInteractableException("Element is not clicable");
            }
        }
    }

    @Override
    public void indeterminate() {
        if (!this.isIndeterminate()) {
            if (this.isClicable()) {
                ((JavascriptExecutor) this.getWrappedDriver()).executeScript("arguments[0].indeterminate = true", this.getWrappedElement());
            } else {
                throw new ElementNotInteractableException("Element is not clicable");
            }
        }
    }

    @Override
    public File downloadImage(String pathname, int connectionTimeout, int readTimeout) {
        File file = new File(pathname);
        String url = this.getAttribute("src");

        try {
            FileUtils.copyURLToFile(new URL(url), file, connectionTimeout, readTimeout);
        } catch (IOException e) {
            throw new WebDriverException("An error ocurred whele downloading an image with url '" + url + "'", e);
        }

        return file;
    }

    @Override
    public File downloadImage(String pathname) {
        File file = new File(pathname);
        String url = this.getAttribute("src");

        try {
            FileUtils.copyURLToFile(new URL(url), file);
        } catch (IOException e) {
            throw new WebDriverException("An error ocurred whele downloading an image with url '" + url + "'", e);
        }

        return file;
    }

    @Override
    public boolean isChecked() {
        return "true".equals(this.getAttribute("checked"));
    }

    @Override
    public boolean isIndeterminate() {
        return "true".equals(this.getAttribute("indeterminate"));
    }

    @Override
    public boolean isDefaultChecked() {
        return "true".equals(this.getAttribute("defaultChecked"));
    }

    @Override
    public void click() {
        this.events().dispatch(e -> e.beforeClickOn(this, this.getWrappedDriver()));

        this.getWrappedElement().click();

        this.events().dispatch(e -> e.afterClickOn(this, this.getWrappedDriver()));
    }

    @Override
    public void submit() {
        this.getWrappedElement().click();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        this.events().dispatch(e -> e.beforeChangeValueOf(this, this.getWrappedDriver(), keysToSend));

        this.getWrappedElement().sendKeys(keysToSend);

        this.events().dispatch(e -> e.afterChangeValueOf(this, this.getWrappedDriver(), keysToSend));
    }

    @Override
    public void clear() {
        this.events().dispatch(e -> e.beforeChangeValueOf(this, this.getWrappedDriver(), null));

        this.getWrappedElement().clear();

        this.events().dispatch(e -> e.afterChangeValueOf(this, this.getWrappedDriver(), null));
    }

    @Override
    public String getTagName() {
        return this.getWrappedElement().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return this.getWrappedElement().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return this.getWrappedElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return this.getWrappedElement().isEnabled();
    }

    @Override
    public String getText() {
        return this.getWrappedElement().getText();
    }

    @Override
    public List<AugmentedWebElement> findElements(By by, String name) {
        this.events().dispatch(e -> e.beforeFindBy(by, this, this.getWrappedDriver()));

        List<WebElement> elements = this.getWrappedElement().findElements(by);
        AugmentedWebElement[] augmentedElements = new AugmentedWebElement[elements.size()];

        if (name == null) {
            for (int i = 0; i < augmentedElements.length; i++) {
                augmentedElements[i] = new DefaultAugmentedWebElement(elements.get(i), null, this.events());
            }
        } else {
            for (int i = 0; i < augmentedElements.length; i++) {
                augmentedElements[i] = new DefaultAugmentedWebElement(elements.get(i), name + " [" + i + "]", this.events());
            }
        }

        this.events().dispatch(e -> e.afterFindBy(by, this, this.getWrappedDriver()));

        return Arrays.asList(augmentedElements);
    }

    @Override
    public AugmentedWebElement findElement(By by, String name) {
        this.events().dispatch(e -> e.beforeFindBy(by, this, this.getWrappedDriver()));

        DefaultAugmentedWebElement element = new DefaultAugmentedWebElement(this.getWrappedElement().findElement(by), name, this.events());

        this.events().dispatch(e -> e.afterFindBy(by, this, this.getWrappedDriver()));

        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WebElement> findElements(By by) {
        return (List<WebElement>) (List<?>) this.findElements(by, null);
    }

    @Override
    public WebElement findElement(By by) {
        return this.findElement(by, null);
    }

    @Override
    public boolean isDisplayed() {
        return this.getWrappedElement().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return this.getWrappedElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return this.getWrappedElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return this.getWrappedElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return this.getWrappedElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return this.getWrappedElement().getScreenshotAs(target);
    }

    @Override
    public void clickAndHold() {
        this.actions().clickAndHold(this.getWrappedElement()).perform();
    }

    @Override
    public void contextClick() {
        this.actions().contextClick(this.getWrappedElement()).perform();
    }

    @Override
    public void doubleClick() {
        this.actions().doubleClick(this.getWrappedElement()).perform();
    }

    @Override
    public void dragAndDrop(WebElement target) {
        this.actions().dragAndDrop(this.getWrappedElement(), target).perform();
    }

    @Override
    public void dragAndDropBy(int xOffset, int yOffset) {
        this.actions().dragAndDropBy(this.getWrappedElement(), xOffset, yOffset).perform();
    }

    @Override
    public void keyDown(CharSequence key) {
        this.actions().keyDown(this.getWrappedElement(), key).perform();
    }

    @Override
    public void keyUp(CharSequence key) {
        this.actions().keyUp(this.getWrappedElement(), key).perform();
    }

    @Override
    public void moveToElement() {
        this.actions().moveToElement(this.getWrappedElement()).perform();
    }

    @Override
    public void moveToElement(int xOffset, int yOffset) {
        this.actions().moveToElement(this.getWrappedElement(), xOffset, yOffset).perform();
    }

    @Override
    public void release() {
        this.actions().release(this.getWrappedElement()).perform();
    }

    @Override
    public EventsRegistry events() {
        return events;
    }

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedDriver;
    }

    @Override
    public Attributes attributes() {
        return attributes;
    }

    @Override
    public Axes axes() {
        return axes;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Coordinates getCoordinates() {
        WebElement element = this.getWrappedElement();

        if (element instanceof Locatable) {
            return ((Locatable) element).getCoordinates();
        }

        return null;
    }

    protected Actions actions() {
        return actions;
    }

    @Override
    public String toString() {
        return this.name();
    }

    /**
     * {@link AugmentedWebElement} {@link AugmentedWebElement.Attributes attributes}.
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
    protected class DefaultAttributes implements AugmentedWebElement.Attributes {

        @Override
        public String id() {
            return getAttribute("id");
        }

        @Override
        public String name() {
            return getAttribute("name");
        }

        @Override
        public List<String> styleClass() {
            String styleClass = getAttribute("class");

            if (styleClass == null) {
                return new ArrayList<>();
            }

            List<String> styleClasses = new ArrayList<>();

            for (String s : styleClass.trim().split(" ")) {
                if (!"".equals(s)) {
                    styleClasses.add(s);
                }
            }

            return styleClasses;
        }

        @Override
        public String title() {
            return getAttribute("title");
        }

        @Override
        public boolean autoFocus() {
            return "true".equals(getAttribute("autofocus"));
        }

        @Override
        public String form() {
            return getAttribute("form");
        }

        @Override
        public URL formAction() {
            String formAction = getAttribute("formaction");

            if (formAction == null) {
                return null;
            }

            try {
                return new URL(formAction);
            } catch (MalformedURLException e) {
                throw new WebDriverException(e);
            }
        }

        @Override
        public FormEncType formEncType() {
            String formEncType = getAttribute("formenctype");

            if (formEncType == null) {
                return null;
            }

            switch (formEncType.trim().toLowerCase()) {
                case "application/x-www-form-urlencoded":
                    return FormEncType.APPLICATION_X_WWW_FORM_ENCODED;

                case "multipart/form-data":
                    return FormEncType.MULTIPART_FORM_DATA;

                case "text/plain":
                    return FormEncType.TEXT_PLAIN;

                default:
                    throw new IllegalArgumentException(formEncType);
            }
        }

        @Override
        public String formMethod() {
            return getAttribute("formmethod");
        }

        @Override
        public boolean formNoValidate() {
            return "true".equals(getAttribute("formnovalidate"));
        }

        @Override
        public String formTarget() {
            return getAttribute("formtarget");
        }

        @Override
        public ButtonType type() {
            String type = getAttribute("type");

            if (type == null) {
                return null;
            }

            switch (type.trim().toLowerCase()) {
                case "button":
                    return ButtonType.BUTTON;

                case "reset":
                    return ButtonType.RESET;

                case "submit":
                    return ButtonType.SUBMIT;

                default:
                    throw new IllegalArgumentException(type);
            }
        }

        @Override
        public String value() {
            return getAttribute("value");
        }

        @Override
        public String defaultValue() {
            return getAttribute("defaultValue");
        }

        @Override
        public boolean checked() {
            return isChecked();
        }

        @Override
        public boolean indeterminate() {
            return isIndeterminate();
        }

        @Override
        public boolean defaultChecked() {
            return isDefaultChecked();
        }

        @Override
        public String href() {
            return getAttribute("href");
        }

        @Override
        public String target() {
            return getAttribute("target");
        }

        @Override
        public String alt() {
            return getAttribute("alt");
        }

        @Override
        public String src() {
            return getAttribute("src");
        }

        @Override
        public String getFor() {
            return getAttribute("for");
        }

        @Override
        public boolean autoComplete() {
            String autoComplete = getAttribute("autocomplete");

            return "on".equals(autoComplete) || "".equals(autoComplete);
        }

        @Override
        public Integer maxLength() {
            String maxLength = getAttribute("maxlength");

            if (maxLength == null) {
                return null;
            }

            return Integer.parseInt(maxLength);
        }

        @Override
        public String pattern() {
            return getAttribute("pattern");
        }

        @Override
        public String placeholder() {
            return getAttribute("placeholder");
        }

        @Override
        public boolean readOnly() {
            return "true".equals(getAttribute("readonly"));
        }

        @Override
        public boolean required() {
            return "true".equals(getAttribute("required"));
        }

        @Override
        public int size() {
            return Integer.parseInt(getAttribute("size"));
        }

        @Override
        public String toString() {
            return "Attributes(" + DefaultAugmentedWebElement.this.toString() + ")";
        }

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
    @ToString
    protected class DefaultAxes implements AugmentedWebElement.Axes {

        @Override
        public AugmentedWebElement parent() {
            return findElement(By.xpath("parent::*"), null);
        }

        @Override
        public List<AugmentedWebElement> handleAxe(String axe, String node) {
            return findElements(By.xpath(axe + "::" + node), null);
        }

        @Override
        public List<AugmentedWebElement> ancestors() {
            return this.handleAxe("ancestor");
        }

        @Override
        public List<AugmentedWebElement> descendants() {
            return this.handleAxe("descendant");
        }

        @Override
        public List<AugmentedWebElement> followings() {
            return this.handleAxe("following");
        }

        @Override
        public List<AugmentedWebElement> followingSiblings() {
            return this.handleAxe("following-sibling");
        }

        @Override
        public List<AugmentedWebElement> precedings() {
            return this.handleAxe("preceding");
        }

        @Override
        public List<AugmentedWebElement> precedingSiblings() {
            return this.handleAxe("preceding-sibling");
        }

    }

}
