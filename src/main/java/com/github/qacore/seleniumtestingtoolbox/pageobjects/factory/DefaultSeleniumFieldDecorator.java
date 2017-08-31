package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.github.qacore.seleniumtestingtoolbox.WebPageFactory;
import com.github.qacore.seleniumtestingtoolbox.annotations.PageComponent;
import com.github.qacore.seleniumtestingtoolbox.annotations.PageRepository;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.internal.SeleniumLocatingElementHandler;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.internal.SeleniumLocatingElementListHandler;
import com.github.qacore.seleniumtestingtoolbox.webdriver.AugmentedWebElement;

import lombok.Data;

/**
 * Default Selenium decorator for use with {@link WebPageFactory} or {@link PageFactory}. Will decorate:
 * <ol>
 * <li>All of the {@link WebElement} fields</li>
 * <li>All of the {@link List}&lt;{@link WebElement}&gt; fields</li>
 * </ol>
 * The fields one and two fields that have {@literal @FindBy}, {@literal @FindBys}, or {@literal @FindAll} annotation with a proxy that locates the elements using the passed in {@link ElementLocatorFactory}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see FieldDecorator
 *
 * @since 1.0.0
 *
 */
@Data
public class DefaultSeleniumFieldDecorator implements FieldDecorator {

    private ElementLocatorFactory elementLocatorFactory;

    public DefaultSeleniumFieldDecorator(ElementLocatorFactory elementLocatorFactory) {
        this.elementLocatorFactory = elementLocatorFactory;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        ElementLocator locator = elementLocatorFactory.createLocator(field);

        if (locator != null) {
            if (WebElement.class.isAssignableFrom(field.getType())) {
                return this.proxyForLocator(loader, locator);
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return this.proxyForListLocator(loader, locator);
            }
        }

        return this.initDefaultPageBeans(loader, field);
    }

    protected Object initDefaultPageBeans(ClassLoader loader, Field field) {
        Object object = this.initDefaultPageBean(PageRepository.class, field, this);

        if (object != null) {
            return object;
        }

        return this.initDefaultPageBean(PageComponent.class, field, this);
    }

    protected Object initDefaultPageBean(Class<? extends Annotation> annotation, Field field, FieldDecorator decorator) {
        Object object = null;

        if (field.isAnnotationPresent(annotation)) {
            try {
                Constructor<?> constructor = field.getType().getConstructor();
                constructor.setAccessible(true);

                object = constructor.newInstance();
            } catch (NoSuchMethodException e) {
                throw new WebDriverException("@" + annotation.getSimpleName() + " field must have an empty constructor.", e);
            } catch (Exception e) {
                throw new WebDriverException("An error ocurred while calling new() instance of @" + annotation.getSimpleName() + ".", e);
            }

            this.proxyFields(this, object, field.getType());
        }

        return object;
    }

    protected boolean isWebElement(Field field) {
        return WebElement.class.isAssignableFrom(field.getType());
    }

    protected boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }

        Type genericType = field.getGenericType();

        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

        if (!WebElement.class.equals(listType)) {
            return false;
        }

        return this.containsElementLocatorMechanism(field);
    }

    protected boolean containsElementLocatorMechanism(Field field) {
        return field.isAnnotationPresent(FindBy.class) || field.isAnnotationPresent(FindBys.class) || field.isAnnotationPresent(FindAll.class);
    }

    protected AugmentedWebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        return (AugmentedWebElement) Proxy.newProxyInstance(loader, new Class[] { AugmentedWebElement.class }, new SeleniumLocatingElementHandler(locator));
    }

    @SuppressWarnings("unchecked")
    protected List<AugmentedWebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
        return (List<AugmentedWebElement>) Proxy.newProxyInstance(loader, new Class[] { List.class }, new SeleniumLocatingElementListHandler(locator));
    }

    protected void proxyFields(FieldDecorator decorator, Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();

        for (Field field : fields) {
            Object value = decorator.decorate(page.getClass().getClassLoader(), field);
            if (value != null) {
                try {
                    field.setAccessible(true);
                    field.set(page, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
