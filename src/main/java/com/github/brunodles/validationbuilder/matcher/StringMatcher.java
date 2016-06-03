package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.validationbuilder.Errors;

import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class StringMatcher {
    private String s;
    private IntConsumer adder;

    StringMatcher(String s, IntConsumer adder) {
        this.s = s;
        this.adder = adder;
    }

    public StringMatcher isEmpty() {
        _if(() -> s.isEmpty(), adder, Errors.EMPTY);
        return this;
    }

    public StringMatcher isNull() {
        _if(() -> s == null, adder, Errors.NULL);
        return this;
    }

    public StringMatcher minLength(int length) {
        _if(() -> s.length() < 8, adder, Errors.MIN_LENGTH);
        return this;
    }

    private static void _if(BooleanSupplier condition, IntConsumer block, int error) {
        try {
            if (condition.getAsBoolean()) block.accept(error);
        } catch (Exception e) {
        }
    }
}
