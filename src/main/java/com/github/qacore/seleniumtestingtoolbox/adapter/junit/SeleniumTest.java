package com.github.qacore.seleniumtestingtoolbox.adapter.junit;

import java.lang.reflect.Field;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.github.qacore.seleniumtestingtoolbox.WebPageFactory;
import com.github.qacore.seleniumtestingtoolbox.annotations.Page;

/**
 * JUnit 4 Selenium Test Runner Adapter.
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
public class SeleniumTest {

    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        public void starting(Description description) {
            WebPageFactory.initElements(SeleniumTest.this);
            
            Class<?> currentClass = SeleniumTest.this.getClass();
            
            while (currentClass != Object.class) {
                for (Field field : currentClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Page.class)) {
                        try {
                            field.setAccessible(true);
                            field.set(SeleniumTest.this, field.getType().newInstance());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                
                currentClass = currentClass.getSuperclass();
            }
        }

    };

}
