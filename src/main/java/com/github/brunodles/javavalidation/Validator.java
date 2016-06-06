package com.github.brunodles.javavalidation;

/**
 * Used to validate some object
 * Created by bruno on 03/06/16.
 */
public interface Validator<T> {
    /**
     * Validates one object
     *
     * @param object the object to be validate
     * @return a {@link ValidationResult}, containing the errors
     */
    ValidationResult validate(T object);
}
