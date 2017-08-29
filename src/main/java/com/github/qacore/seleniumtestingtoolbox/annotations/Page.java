package com.github.qacore.seleniumtestingtoolbox.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.github.qacore.seleniumtestingtoolbox.WebPageFactory;

/**
 * Mark a field as a Page Object that should be automatically created by Selenium Testing Toolbox.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see WebPageFactory
 *
 * @since 1.0.0
 *
 */
@Inherited
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface Page {

}
