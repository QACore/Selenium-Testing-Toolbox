package com.github.qacore.seleniumtestingtoolbox.webdriver;

import java.io.File;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

/**
 * Augmented {@link TakesScreenshot}.
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
public interface AugmentedTakesScreenshot extends TakesScreenshot {

    /**
     * Take a snapshot of the browser into a file given by the fileName param.
     *
     * @param fileName
     *            file name for screenshot
     * 
     * @return The file.
     * 
     * @throws WebDriverException
     *             On screenshot failure.
     */
    File takeScreenShot(String fileName) throws WebDriverException;

}
