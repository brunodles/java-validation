package com.github.brunodles.validationbuilder;

import java.util.HashMap;
import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class ValidationResultBuilder implements ValidationResult {
    HashMap<String, Integer> errorMap;

    public ValidationResultBuilder() {
        errorMap = new HashMap<>();
    }

    public IntConsumer adder(String key) {
        return error -> add(key, error);
    }

    public void add(String key, int error) {
        Integer errors = errorsFrom(key);
        errors |= error;
        errorMap.put(key, errors);
    }

    private Integer errorsFrom(String key) {
        Integer errors = errorMap.get(key);
        if (errors == null) errors = 0;
        return errors;
    }

    public boolean isValid() {
        return errorMap.isEmpty();
    }

    @Override
    public boolean contains(String key, int error) {
        Integer errors = errorsFrom(key);
        return (errors & error) == error;
    }
}
