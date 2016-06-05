package com.github.brunodles.validationbuilder;

/**
 * With this interface is possible to know what is wrong
 * Created by bruno on 03/06/16.
 */
public interface ValidationResult {
    /**
     * This is the return of the whole tests.
     *
     * @return true if didn't found any error
     */
    boolean isValid();


    /**
     * With this you can check if one key have certain error.
     *
     * @param key   normaly is used the field name
     * @param error the error from {@link Errors}
     * @return true if the key contains the error
     */
    boolean contains(String key, int error);
}
