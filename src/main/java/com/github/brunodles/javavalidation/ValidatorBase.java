package com.github.brunodles.javavalidation;

/**
 * This class is intent to be used to simplify the implementation of the Validator.
 * To use it you just need to implement the method validate.
 * <p>
 * Created by bruno on 03/06/16.
 */
public abstract class ValidatorBase<T> implements Validator<T> {

    public ValidationResult validate(T object) {
        ValidationResultBuilder resultBuilder = new ValidationResultBuilder();
        validate(object, resultBuilder);
        return resultBuilder;
    }

    abstract void validate(T object, ValidationResultBuilder resultBuilder);
}
