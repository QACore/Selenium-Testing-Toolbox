package com.github.qacore.seleniumtestingtoolbox.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;

import com.github.qacore.seleniumtestingtoolbox.WebDriverContext;

/**
 * This abstract class indicates that the classes executes the basic actions of the <b>Page Object</b> pattern.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @param <I>
 *            Itself class for fluent pattern.
 *
 * @since 1.0.0
 * 
 * @see WebDriverContext
 *
 */
public abstract class AbstractPage<I extends AbstractPage<I>> extends WebDriverContext {

    public AbstractPage(WrapsDriver driverContext) {
        super(driverContext);
        
        ParallelPageFactory.initElements(this);
    }

    public AbstractPage(WebDriver webDriver) {
        super(webDriver);
        
        ParallelPageFactory.initElements(this);
    }

    public AbstractPage() {
        super();
        
        ParallelPageFactory.initElements(this);
    }

    /**
     * Load the page in the current browser window.
     * 
     * @param path
     *            The URL path after {@code getUrl()}.
     * 
     * @return Itself.
     */
    public I load(String path) {
        this.getWrappedDriver().get(this.getUrl() + path);

        return itself();
    }

    /**
     * Load the page in the current browser window.
     * 
     * @return Itself.
     */
    public I load() {
        return this.load("");
    }

    /**
     * Determine whether or not the page is loaded. When the page is loaded, this method will return, but when it is not loaded, an Error should be thrown. This also allows for complex checking and error reporting when loading a page, which in turn supports better error reporting when a page fails to load.
     *
     * <p>
     * This behaviour makes it readily visible when a page has not been loaded successfully, and because an error and not an exception is thrown tests should fail as expected. By using Error, we also allow the use of junit's "Assert.assert*" methods.
     * </p>
     * 
     * @throws Error
     *             When the page is not loaded.
     */
    public abstract void isLoaded() throws Error;

    /**
     * The URL to load. It is best to use a fully qualified URL.
     * 
     * @return The URL to load.
     */
    public abstract String getUrl();

    /**
     * The generic parameter.
     * 
     * @return Itself.
     */
    @SuppressWarnings("unchecked")
    protected I itself() {
        return (I) this;
    }

    @Override
    public String toString() {
        return "Page(" + this.getUrl() + ")";
    }

}
