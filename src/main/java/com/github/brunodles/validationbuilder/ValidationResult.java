package com.github.brunodles.validationbuilder;

/**
 * Created by bruno on 03/06/16.
 */
public interface ValidationResult {
    boolean isValid();

    boolean contains(String key, int error);
}
