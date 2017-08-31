package com.github.qacore.seleniumtestingtoolbox.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Identifies an Ajax Element.
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

@Target(FIELD)
@Retention(RUNTIME)
public @interface AjaxElement {

    /**
     * The timeout for the element.
     * 
     * @return The timeout duration.
     */
    long value() default 5L;

    /**
     * The timeout unit for the element.
     * 
     * @return The unit of time.
     */
    TimeUnit unit() default TimeUnit.SECONDS;

}
