package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.validationbuilder.Errors;

import java.util.function.IntConsumer;

import static com.github.brunodles.validationbuilder.matcher.Common._if;

/**
 * Created by bruno on 04/06/16.
 */
public class BooleanMatcher implements ObjectMatcher<Boolean, BooleanMatcher> {

    Boolean value;
    IntConsumer adder;

    public BooleanMatcher(Boolean value, IntConsumer adder) {
        this.value = value;
        this.adder = adder;
    }

    /**
     * Check if the reference value is true
     */
    public BooleanMatcher isTrue() {
        _if(() -> value, adder, Errors.TRUE);
        return this;
    }

    /**
     * Check if the reference value is false
     */
    public BooleanMatcher isFalse() {
        _if(() -> !value, adder, Errors.FALSE);
        return this;
    }

    /**
     * Check if the reference value is null
     */
    @Override
    public BooleanMatcher isNull() {
        _if(() -> value == null, adder, Errors.NULL);
        return this;
    }

    /**
     * Check if the reference value is equals to {@param expected}
     *
     * @param expected another boolean
     */
    @Override
    public BooleanMatcher isEqualsTo(Boolean expected) {
        _if(() -> value.equals(expected), adder, Errors.EQUAL);
        return this;
    }
}
