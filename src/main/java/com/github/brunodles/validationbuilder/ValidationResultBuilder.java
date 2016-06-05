package com.github.brunodles.validationbuilder;

import com.github.brunodles.validationbuilder.matcher.When;

import java.util.HashMap;
import java.util.function.IntConsumer;

/**
 * This builder will be used inside the validator to create all validations.
 * Created by bruno on 03/06/16.
 */
public class ValidationResultBuilder implements ValidationResult {
    HashMap<String, Integer> errorMap;

    public ValidationResultBuilder() {
        errorMap = new HashMap<>();
    }

    /**
     * Use this method to add an error.
     *
     * @param key   This key will be used to know what is wrong, you ca use the field name here.
     * @param error All errors are constants, use one of {@link Errors}
     */
    public void add(String key, int error) {
        Integer errors = errorsFrom(key);
        errors |= error;
        errorMap.put(key, errors);
    }

    /**
     * This will return one error adder for the key
     *
     * @param key the key you want to add error
     * @return a {@link IntConsumer}, to add errors on the key just call {@link IntConsumer#accept(int)}
     */
    public IntConsumer adder(String key) {
        return error -> add(key, error);
    }

    /**
     * With this method you can get the errors from one key
     *
     * @param key
     * @return
     */
    private Integer errorsFrom(String key) {
        Integer errors = errorMap.get(key);
        if (errors == null) errors = 0;
        return errors;
    }

    /**
     * Check if the object is valid
     *
     * @return true if the object don't have any error
     */
    public boolean isValid() {
        return errorMap.isEmpty();
    }

    /**
     * With this you can check if one key have certain error.
     *
     * @param key   the same key used on {@link #add}
     * @param error the error from {@link Errors}
     * @return true if the {@param key} contains the {@param error}
     */
    @Override
    public boolean contains(String key, int error) {
        Integer errors = errorsFrom(key);
        return (errors & error) == error;
    }

    /**
     * With this you can use a builder to create all errors for one key
     *
     * @param key the key where the errors will be added
     * @return a helper to build the errors {@link When}
     */
    public When addTo(String key) {
        return new When(adder(key));
    }
}
