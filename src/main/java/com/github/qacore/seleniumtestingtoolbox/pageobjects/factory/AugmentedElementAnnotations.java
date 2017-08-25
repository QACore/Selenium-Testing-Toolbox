package com.github.qacore.seleniumtestingtoolbox.pageobjects.factory;

import java.lang.reflect.Field;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.Annotations;

import com.github.qacore.seleniumtestingtoolbox.annotations.Name;

import lombok.Getter;

/**
 * Class to work with fields in Page Objects. Provides methods to process:
 * <ul>
 * <li>@{@link FindBy}</li>
 * <li>@{@link FindBys}</li>
 * <li>@{@link FindAll}</li>
 * <li>@{@link Name}</li>
 * </ul>
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
public class AugmentedElementAnnotations extends Annotations {

    @Getter
    private String name;

    @Getter
    private String description;

    public AugmentedElementAnnotations(Field field) {
        super(field);

        Name name = field.getAnnotation(Name.class);

        if (name != null) {
            this.name = name.value();
            this.description = name.description();
        }
    }

}
