package com.github.brunodles.validationbuilder;

/**
 * Created by bruno on 03/06/16.
 */
public abstract class ValidatorBase<T> implements Validator<T> {

    public ValidationResult validate(T object){
        ValidationResultBuilder resultBuilder = new ValidationResultBuilder();
        validate(object, resultBuilder);
        return resultBuilder;
    }

    abstract void validate(T object, ValidationResultBuilder resultBuilder);
}
