package com.github.qacore.seleniumtestingtoolbox.webdriver;

import static lombok.AccessLevel.PROTECTED;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.security.Credentials;

import com.github.qacore.seleniumtestingtoolbox.webdriver.events.EventsRegistry;
import com.github.qacore.seleniumtestingtoolbox.webdriver.html5.JSLocalStorage;
import com.github.qacore.seleniumtestingtoolbox.webdriver.html5.JSSessionStorage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Default {@link AugmentedWebDriver}.
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
@ToString(exclude = { "eventsRegistry" })
public class DefaultAugmentedWebDriver implements AugmentedWebDriver {

    private final EventsRegistry eventsRegistry = new EventsRegistry();

    @Getter
    @Setter(PROTECTED)
    private WebDriver            wrappedDriver;

    @Getter(PROTECTED)
    @Setter(PROTECTED)
    private TargetLocator        targetLocator;

    @Getter(PROTECTED)
    @Setter(PROTECTED)
    private Navigation           navigation;

    @Getter(PROTECTED)
    @Setter(PROTECTED)
    private Options              options;

    public DefaultAugmentedWebDriver(WebDriver webDriver) {
        this.wrappedDriver = webDriver;
    }

    @Override
    public void getInNewTab(String url) {
        if (url == null) {
            throw new IllegalArgumentException("Url is mandatory");
        }

        this.openNewTab();
        this.get(url);
    }

    @Override
    public void openNewTab() {
        synchronized (this.getClass()) {
            WebDriver driver = this.getWrappedDriver();
            Set<String> initialTabs = driver.getWindowHandles();

            this.executeScript("window.open('', '_blank');");

            Set<String> tabs = driver.getWindowHandles();

            tabs.removeAll(initialTabs);
            driver.switchTo().window(tabs.iterator().next());
        }
    }

    @Override
    public void get(String url) {
        this.events().dispatch(e -> e.beforeNavigateTo(url, this));

        this.getWrappedDriver().get(url);

        this.events().dispatch(e -> e.afterNavigateTo(url, this));
    }

    @Override
    public String getCurrentUrl() {
        return this.getWrappedDriver().getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return this.getWrappedDriver().getTitle();
    }

    @Override
    public List<AugmentedWebElement> findElements(By by, String name) {
        this.events().dispatch(e -> e.beforeFindBy(by, null, this.getWrappedDriver()));

        List<AugmentedWebElement> elements = SearchContextHolder.findElements(this.getWrappedDriver(), by, name, this.events());

        this.events().dispatch(e -> e.afterFindBy(by, null, this.getWrappedDriver()));

        return elements;
    }

    @Override
    public AugmentedWebElement findElement(By by, String name) {
        this.events().dispatch(e -> e.beforeFindBy(by, null, this.getWrappedDriver()));

        DefaultAugmentedWebElement element = new DefaultAugmentedWebElement(this.getWrappedDriver().findElement(by), name, this.events());

        this.events().dispatch(e -> e.afterFindBy(by, null, this.getWrappedDriver()));

        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WebElement> findElements(By by) {
        return (List<WebElement>) (List<? extends WebElement>) this.findElements(by, null);
    }

    @Override
    public WebElement findElement(By by) {
        return this.findElement(by, null);
    }

    @Override
    public String getPageSource() {
        return this.getWrappedDriver().getPageSource();
    }

    @Override
    public void close() {
        this.getWrappedDriver().close();
    }

    @Override
    public void quit() {
        this.getWrappedDriver().quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.getWrappedDriver().getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.getTargetLocator();
    }

    @Override
    public Navigation navigate() {
        return this.getNavigation();
    }

    @Override
    public Options manage() {
        return this.getOptions();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof JavascriptExecutor) {
            this.events().dispatch(e -> e.beforeScript(script, driver));

            Object result = ((JavascriptExecutor) driver).executeScript(script, args);

            this.events().dispatch(e -> e.afterScript(script, driver));

            return result;
        }

        throw new UnsupportedOperationException("Wrapped driver instance does not support executing javascript");
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof JavascriptExecutor) {
            this.events().dispatch(e -> e.beforeScript(script, driver));

            Object result = ((JavascriptExecutor) driver).executeAsyncScript(script, args);

            this.events().dispatch(e -> e.afterScript(script, driver));

            return result;
        }

        throw new UnsupportedOperationException("Wrapped driver instance does not support executing javascript");
    }

    @Override
    public File takeScreenShot(String fileName) throws WebDriverException {
        File scrFile = this.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fileName);

        try {
            FileUtils.copyDirectory(scrFile, destFile);
        } catch (IOException e) {
            throw new WebDriverException(e);
        } finally {
            FileUtils.deleteQuietly(scrFile);
        }

        return destFile;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(target);
        }

        throw new UnsupportedOperationException("Wrapped driver instance does not support taking screenshots");
    }

    @Override
    public EventsRegistry events() {
        return eventsRegistry;
    }

    @Override
    public Keyboard getKeyboard() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof HasInputDevices) {
            return ((HasInputDevices) driver).getKeyboard();
        }

        throw new UnsupportedOperationException("Wrapped driver does not implement advanced user interactions yet.");
    }

    @Override
    public Mouse getMouse() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof HasInputDevices) {
            return ((HasInputDevices) driver).getMouse();
        }

        throw new UnsupportedOperationException("Wrapped driver does not implement advanced user interactions yet.");
    }

    @Override
    public Capabilities getCapabilities() {
        WebDriver driver = this.getWrappedDriver();
        Capabilities capabilities = driver instanceof HasCapabilities ? ((HasCapabilities) driver).getCapabilities() : null;

        while (driver instanceof WrapsDriver && capabilities == null) {
            driver = ((WrapsDriver) driver).getWrappedDriver();
            capabilities = driver instanceof HasCapabilities ? ((HasCapabilities) driver).getCapabilities() : null;
        }

        return capabilities;
    }

    @Override
    public TouchScreen getTouch() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof HasTouchScreen) {
            return ((HasTouchScreen) driver).getTouch();
        }

        throw new UnsupportedOperationException("Wrapped driver does not implement advanced user interactions yet.");
    }

    @Override
    public void perform(Collection<Sequence> actions) {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof Interactive) {
            ((Interactive) driver).perform(actions);
        } else {
            throw new UnsupportedOperationException("Wrapped driver does not implement advanced user interactions yet.");
        }
    }

    @Override
    public void resetInputState() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof Interactive) {
            ((Interactive) driver).resetInputState();
        } else {
            throw new UnsupportedOperationException("Wrapped driver does not implement advanced user interactions yet.");
        }
    }

    @Override
    public LocalStorage getLocalStorage() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof WebStorage) {
            return ((WebStorage) driver).getLocalStorage();
        }

        return new JSLocalStorage((WrapsDriver) this);
    }

    @Override
    public SessionStorage getSessionStorage() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof WebStorage) {
            return ((WebStorage) driver).getSessionStorage();
        }

        return new JSSessionStorage((WrapsDriver) this);
    }

    @Override
    public ConnectionType getNetworkConnection() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof NetworkConnection) {
            return ((NetworkConnection) driver).getNetworkConnection();
        }

        throw new UnsupportedOperationException("Wrapped driver does not implements NetworkConnection yet");
    }

    @Override
    public ConnectionType setNetworkConnection(ConnectionType type) {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof NetworkConnection) {
            return ((NetworkConnection) driver).setNetworkConnection(type);
        }

        throw new UnsupportedOperationException("Wrapped driver does not implements NetworkConnection yet");
    }

    @Override
    public Location location() {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof LocationContext) {
            return ((LocationContext) driver).location();
        }

        throw new UnsupportedOperationException("Wrapped driver does not implements LocationContext yet");
    }

    @Override
    public void setLocation(Location location) {
        WebDriver driver = this.getWrappedDriver();

        if (driver instanceof LocationContext) {
            ((LocationContext) driver).setLocation(location);
        } else {
            throw new UnsupportedOperationException("Wrapped driver does not implements LocationContext yet");
        }
    }

    /**
     * Default {@link AugmentedWebDriver.TargetLocator}.
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
    @ToString
    protected class DefaultTargetLocator implements AugmentedWebDriver.TargetLocator {

        @Getter(PROTECTED)
        @Setter(PROTECTED)
        private AugmentedAlert alert = new DefaultAlert();

        @Override
        public WebElement activeElement() {
            return getWrappedDriver().switchTo().activeElement();
        }

        @Override
        public AugmentedAlert alert() {
            return this.getAlert();
        }

        @Override
        public AugmentedWebDriver frame(int index) {
            getWrappedDriver().switchTo().frame(index);

            return DefaultAugmentedWebDriver.this;
        }

        @Override
        public AugmentedWebDriver frame(String nameOrId) {
            getWrappedDriver().switchTo().frame(nameOrId);

            return DefaultAugmentedWebDriver.this;
        }

        @Override
        public AugmentedWebDriver frame(WebElement frameElement) {
            getWrappedDriver().switchTo().frame(frameElement);

            return DefaultAugmentedWebDriver.this;
        }

        @Override
        public AugmentedWebDriver parentFrame() {
            getWrappedDriver().switchTo().parentFrame();

            return DefaultAugmentedWebDriver.this;
        }

        @Override
        public AugmentedWebDriver window(String nameOrHandle) {
            getWrappedDriver().switchTo().window(nameOrHandle);

            return DefaultAugmentedWebDriver.this;
        }

        @Override
        public AugmentedWebDriver window(int index) {
            WebDriver webDriver = getWrappedDriver();
            Set<String> windowHandles = webDriver.getWindowHandles();

            if (windowHandles.size() > index) {
                throw new WebDriverException("Invalid tab index '" + index + "'. Current tab count '" + windowHandles.size() + "'.");
            }

            webDriver.switchTo().window(new ArrayList<>(windowHandles).get(index));

            return DefaultAugmentedWebDriver.this;
        }

        @Override
        public AugmentedWebDriver defaultContent() {
            getWrappedDriver().switchTo().defaultContent();

            return DefaultAugmentedWebDriver.this;
        }

    }

    /**
     * Default {@link AugmentedWebDriver.Navigation}.
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
    @ToString
    protected class DefaultNavigation implements AugmentedWebDriver.Navigation {

        @Override
        public void back() {
            events().dispatch(e -> e.beforeNavigateBack(DefaultAugmentedWebDriver.this));

            getWrappedDriver().navigate().back();

            events().dispatch(e -> e.afterNavigateBack(DefaultAugmentedWebDriver.this));
        }

        @Override
        public void forward() {
            events().dispatch(e -> e.beforeNavigateForward(DefaultAugmentedWebDriver.this));

            getWrappedDriver().navigate().forward();

            events().dispatch(e -> e.afterNavigateForward(DefaultAugmentedWebDriver.this));
        }

        @Override
        public void to(String url) {
            events().dispatch(e -> e.beforeNavigateTo(url, DefaultAugmentedWebDriver.this));

            getWrappedDriver().navigate().to(url);

            events().dispatch(e -> e.afterNavigateTo(url, DefaultAugmentedWebDriver.this));
        }

        @Override
        public void to(URL url) {
            this.to(String.valueOf(url));
        }

        @Override
        public void refresh() {
            events().dispatch(e -> e.beforeNavigateRefresh(DefaultAugmentedWebDriver.this));

            getWrappedDriver().navigate().refresh();

            events().dispatch(e -> e.afterNavigateRefresh(DefaultAugmentedWebDriver.this));
        }

    }

    /**
     * Default {@link AugmentedWebDriver.Options}.
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
    @ToString
    protected class DefaultOptions implements AugmentedWebDriver.Options {

        @Getter(PROTECTED)
        @Setter(PROTECTED)
        private Timeouts   timeouts   = new DefaultTimeouts();

        @Getter(PROTECTED)
        @Setter(PROTECTED)
        private ImeHandler imeHandler = new DefaultImeHandler();

        @Getter(PROTECTED)
        @Setter(PROTECTED)
        private Window     window     = new DefaultWindow();

        @Override
        public void addCookie(Cookie cookie) {
            getWrappedDriver().manage().addCookie(cookie);
        }

        @Override
        public void deleteCookieNamed(String name) {
            getWrappedDriver().manage().deleteCookieNamed(name);
        }

        @Override
        public void deleteCookie(Cookie cookie) {
            getWrappedDriver().manage().deleteCookie(cookie);
        }

        @Override
        public void deleteAllCookies() {
            getWrappedDriver().manage().deleteAllCookies();
        }

        @Override
        public Set<Cookie> getCookies() {
            return getWrappedDriver().manage().getCookies();
        }

        @Override
        public Cookie getCookieNamed(String name) {
            return getWrappedDriver().manage().getCookieNamed(name);
        }

        @Override
        public Logs logs() {
            return getWrappedDriver().manage().logs();
        }

        @Override
        public Timeouts timeouts() {
            return this.getTimeouts();
        }

        @Override
        public ImeHandler ime() {
            return this.getImeHandler();
        }

        @Override
        public Window window() {
            return this.getWindow();
        }

        /**
         * Default {@link AugmentedWebDriver.Timeouts}.
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
        @ToString
        protected class DefaultTimeouts implements AugmentedWebDriver.Timeouts {

            @Getter
            private Duration implicityWait;

            @Getter
            private Duration scriptTimeout;

            @Getter
            private Duration pageLoadTimeout;

            @Override
            public Timeouts implicitlyWait(Duration duration) {
                getWrappedDriver().manage().timeouts().implicitlyWait(duration.getTime(), duration.getUnit());

                this.implicityWait = duration;

                return this;
            }

            @Override
            public Timeouts implicitlyWait(long time, TimeUnit unit) {
                return this.implicitlyWait(new Duration(time, unit));
            }

            @Override
            public Timeouts setScriptTimeout(Duration duration) {
                getWrappedDriver().manage().timeouts().setScriptTimeout(duration.getTime(), duration.getUnit());

                this.scriptTimeout = duration;

                return null;
            }

            @Override
            public Timeouts setScriptTimeout(long time, TimeUnit unit) {
                return this.setScriptTimeout(new Duration(time, unit));
            }

            @Override
            public Timeouts pageLoadTimeout(Duration duration) {
                getWrappedDriver().manage().timeouts().pageLoadTimeout(duration.getTime(), duration.getUnit());

                this.pageLoadTimeout = duration;

                return null;
            }

            @Override
            public Timeouts pageLoadTimeout(long time, TimeUnit unit) {
                return this.pageLoadTimeout(new Duration(time, unit));
            }

        }

        /**
         * Default {@link AugmentedWebDriver.ImeHandler}.
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
        @ToString
        protected class DefaultImeHandler implements AugmentedWebDriver.ImeHandler {

            @Override
            public List<String> getAvailableEngines() {
                return getWrappedDriver().manage().ime().getAvailableEngines();
            }

            @Override
            public String getActiveEngine() {
                return getWrappedDriver().manage().ime().getActiveEngine();
            }

            @Override
            public boolean isActivated() {
                return getWrappedDriver().manage().ime().isActivated();
            }

            @Override
            public void deactivate() {
                getWrappedDriver().manage().ime().deactivate();
            }

            @Override
            public void activateEngine(String engine) {
                getWrappedDriver().manage().ime().activateEngine(engine);
            }

        }

        /**
         * Default {@link AugmentedWebDriver.Window}.
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
        @ToString
        protected class DefaultWindow implements AugmentedWebDriver.Window {

            @Override
            public void setSize(Dimension targetSize) {
                getWrappedDriver().manage().window().setSize(targetSize);
            }

            @Override
            public void setPosition(Point targetPosition) {
                getWrappedDriver().manage().window().setPosition(targetPosition);
            }

            @Override
            public Dimension getSize() {
                return getWrappedDriver().manage().window().getSize();
            }

            @Override
            public Point getPosition() {
                return getWrappedDriver().manage().window().getPosition();
            }

            @Override
            public void maximize() {
                getWrappedDriver().manage().window().maximize();
            }

            @Override
            public void fullscreen() {
                getWrappedDriver().manage().window().fullscreen();
            }

        }

    }

    /**
     * Default {@link AugmentedAlert}.
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
    @ToString
    protected class DefaultAlert implements AugmentedAlert {

        @Override
        public void dismiss() {
            events().dispatch(e -> e.beforeAlertDismiss(DefaultAugmentedWebDriver.this));

            getWrappedDriver().switchTo().alert().dismiss();

            events().dispatch(e -> e.afterAlertDismiss(DefaultAugmentedWebDriver.this));
        }

        @Override
        public void accept() {
            events().dispatch(e -> e.beforeAlertAccept(DefaultAugmentedWebDriver.this));

            getWrappedDriver().switchTo().alert().accept();

            events().dispatch(e -> e.afterAlertAccept(DefaultAugmentedWebDriver.this));
        }

        @Override
        public String getText() {
            return getWrappedDriver().switchTo().alert().getText();
        }

        @Override
        public void sendKeys(String keysToSend) {
            getWrappedDriver().switchTo().alert().sendKeys(keysToSend);
        }

        @Override
        public void setCredentials(Credentials credentials) {
            getWrappedDriver().switchTo().alert().setCredentials(credentials);
        }

        @Override
        public void authenticateUsing(Credentials credentials) {
            getWrappedDriver().switchTo().alert().authenticateUsing(credentials);
        }

    }

}
