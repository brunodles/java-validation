package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.validationbuilder.Errors;

import java.util.function.IntConsumer;

import static com.github.brunodles.validationbuilder.matcher.Common._if;

/**
 * Created by bruno on 03/06/16.
 */
public class IntegerMatcher {
    private Integer value;
    private IntConsumer adder;

    public IntegerMatcher(Integer value, IntConsumer adder) {
        this.value = value;
        this.adder = adder;
    }

    public IntegerMatcher isEqualsTo(int expected) {
        _if(() -> value == expected, adder, Errors.EQUAL);
        return this;
    }

    public IntegerMatcher isGreater(int expected) {
        _if(() -> value > expected, adder, Errors.GREATER);
        return this;
    }

    public IntegerMatcher isLower(int expected) {
        _if(() -> value < expected, adder, Errors.LOWER);
        return this;
    }

    public IntegerMatcher isBetween(int min, int max) {
        _if(() -> (value > min && value < max), adder, Errors.BETWEEN);
        return this;
    }

    public IntegerMatcher isNull() {
        _if(() -> value == null, adder, Errors.NULL);
        return this;
    }


}
