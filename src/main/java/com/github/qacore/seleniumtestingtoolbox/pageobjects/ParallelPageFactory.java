package com.github.qacore.seleniumtestingtoolbox.pageobjects;

import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.PageFactory;

import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.DefaultParallelElementLocatorFactory;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.DefaultParallelFieldDecorator;

/**
 * Parallel Factory class to make using Page Objects simpler and easier.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 */
public final class ParallelPageFactory {

    public static void initElements(AbstractPage<?> page) {
        PageFactory.initElements(new DefaultParallelFieldDecorator(new DefaultParallelElementLocatorFactory(page)), page);
    }

    public static void initElements(WrapsDriver page) {
        PageFactory.initElements(new DefaultParallelFieldDecorator(new DefaultParallelElementLocatorFactory(page)), page);
    }

    public static void initElements(Object page) {
        PageFactory.initElements(new DefaultParallelFieldDecorator(new DefaultParallelElementLocatorFactory()), page);
    }

    private ParallelPageFactory() {

    }

}
