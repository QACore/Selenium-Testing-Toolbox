package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;

import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsControl;
import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsRegistry;

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
        return this.findElement(by);
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

}
