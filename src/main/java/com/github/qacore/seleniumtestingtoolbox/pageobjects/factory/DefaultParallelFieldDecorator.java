package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.github.qacore.seleniumtestingtoolbox.pageobjects.ParallelPageFactory;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.internal.ParallelLocatingElementHandler;
import com.github.qacore.seleniumtestingtoolbox.pageobjects.factory.internal.ParallelLocatingElementListHandler;
import com.github.qacore.seleniumtestingtoolbox.stereotype.PageComponent;

import lombok.Data;

/**
 * Default parallel decorator for use with {@link ParallelPageFactory} or {@link PageFactory}. Will decorate:
 * <ol>
 * <li>All of the {@link WebElement} fields</li>
 * <li>All of the {@link List}&lt;{@link WebElement}&gt; fields</li>
 * </ol>
 * The fields one and two fields that have {@literal @FindBy}, {@literal @FindBys}, or {@literal @FindAll} annotation with a proxy that locates the elements using the passed in {@link ParallelElementLocatorFactory}.
 * 
 * @author Leonardo Carmona da Silva
 *         <ul>
 *         <li><a href="https://br.linkedin.com/in/l3ocarmona">https://br.linkedin.com/in/l3ocarmona</a></li>
 *         <li><a href="https://github.com/leocarmona">https://github.com/leocarmona</a></li>
 *         <li><a href="mailto:lcdesenv@gmail.com">lcdesenv@gmail.com</a></li>
 *         </ul>
 *
 * @see ParallelFieldDecorator
 *
 * @since 1.0.0
 *
 */
@Data
public class DefaultParallelFieldDecorator implements ParallelFieldDecorator {

    private ParallelElementLocatorFactory parallelElementLocatorFactory;

    public DefaultParallelFieldDecorator(ParallelElementLocatorFactory parallelElementLocatorFactory) {
        this.parallelElementLocatorFactory = parallelElementLocatorFactory;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (!this.hasSupport(field)) {
            return null;
        }

        ParallelElementLocator locator = this.getParallelElementLocatorFactory().createLocator(field);

        if (locator == null) {
            return null;
        }

        if (WebElement.class.isAssignableFrom(field.getType())) {
            return this.proxyForLocator(loader, locator);
        }

        if (List.class.isAssignableFrom(field.getType())) {
            return this.proxyForListLocator(loader, locator);
        }

        Object object = null;

        if (field.isAnnotationPresent(PageComponent.class)) {
            try {
                Constructor<?> constructor = field.getType().getConstructor();
                constructor.setAccessible(true);
                
                object = constructor.newInstance();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("@PageComponent field must have an empty constructor.", e);
            } catch (Exception e) {
                throw new RuntimeException("An error ocurred while calling new() instance of @PageComponent.", e);
            }

            this.proxyFields(this, object, field.getType());
        }

        return object;
    }
    
    protected boolean hasSupport(Field field) {
        return WebElement.class.isAssignableFrom(field.getType()) || this.isDecoratableList(field) || field.isAnnotationPresent(PageComponent.class);
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

    protected WebElement proxyForLocator(ClassLoader loader, ParallelElementLocator locator) {
        return (WebElement) Proxy.newProxyInstance(loader, new Class[] { WebElement.class, WrapsElement.class, Locatable.class }, new ParallelLocatingElementHandler(locator));
    }

    @SuppressWarnings("unchecked")
    protected List<WebElement> proxyForListLocator(ClassLoader loader, ParallelElementLocator locator) {
        return (List<WebElement>) Proxy.newProxyInstance(loader, new Class[] { List.class }, new ParallelLocatingElementListHandler(locator));
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
