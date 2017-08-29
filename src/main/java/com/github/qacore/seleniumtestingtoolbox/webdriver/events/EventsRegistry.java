package com.github.qacore.seleniumtestingtoolbox.webdriver.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import lombok.ToString;

/**
 * Registry of event listeners.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see AugmentedWebDriverEventListener
 * @see AbstractWebDriverEventListener
 *
 * @since 1.0.0
 *
 */
@ToString
public class EventsRegistry {

    private List<AugmentedWebDriverEventListener> events;

    public EventsRegistry() {
        events = new ArrayList<>();
    }

    /**
     * Register a new event listener.
     *
     * @param eventListener
     *            Event listener to register.
     * 
     * @return {@code this} to chain method calls.
     * 
     * @see AbstractAugmentedWebDriverEventListener
     */
    public EventsRegistry register(AugmentedWebDriverEventListener eventListener) {
        events.add(eventListener);

        return this;
    }

    /**
     * Unregister an existing event listener.
     *
     * @param eventListener
     *            Existing event listener to unregister;
     * 
     * @return {@code this} to chain method calls.
     *
     * @see AbstractAugmentedWebDriverEventListener
     */
    public EventsRegistry unregister(AugmentedWebDriverEventListener eventListener) {
        events.remove(eventListener);

        return this;
    }

    /**
     * Dispatch all the events. Example:
     * <ul>
     * <li>{@code events.dispatch(e -> e.beforeClickOn(WebElement, WebDriver));}</li>
     * </ul>
     * 
     * @param action
     *            A non-interfering action to perform on the elements.
     */
    public void dispatch(Consumer<? super AugmentedWebDriverEventListener> action) {
        events.stream().forEach(action);
    }

    /**
     * Unregister all event listeners.
     */
    public void close() {
        events.clear();
    }

}
