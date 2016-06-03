package com.github.brunodles.validationbuilder;

import com.github.brunodles.validationbuilder.matcher.When;

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

    public void add(String key, int error) {
        Integer errors = errorsFrom(key);
        errors |= error;
        errorMap.put(key, errors);
    }

    public IntConsumer adder(String key) {
        return error -> add(key, error);
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

    public When addTo(String key) {
        return new When(adder(key));
    }
}
