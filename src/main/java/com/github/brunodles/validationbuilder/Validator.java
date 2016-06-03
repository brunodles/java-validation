package com.github.brunodles.validationbuilder;

/**
 * Created by bruno on 03/06/16.
 */
public interface Validator<T> {
    ValidationResult validate(T object);
}
