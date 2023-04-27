package com.annotations;

import com.enums.Author;
import com.enums.Category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to describe a test method.
 * It provides information about the test's description, authors, and categories.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestDescription {

    /**
     * Returns a description of the test method.
     *
     * @return the description of the test method.
     */
    String description();

    /**
     * Returns an array of authors who wrote the test method.
     *
     * @return the authors who wrote the test method.
     */
    Author[] author();

    /**
     * Returns an array of categories that the test method belongs to.
     *
     * @return the categories that the test method belongs to.
     */
    Category[] category();
}
