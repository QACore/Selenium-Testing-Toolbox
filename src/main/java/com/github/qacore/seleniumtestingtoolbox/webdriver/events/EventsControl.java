package com.github.qacore.seleniumtestingtoolbox.webdriver.events;

/**
 * Control interface for events.
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
public interface EventsControl {

    /**
     * Retrieves an event registry to register event listeners.
     *
     * @return The event registry.
     */
    public EventsRegistry events();

}
