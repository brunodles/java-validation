package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.validationbuilder.Errors;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import static com.github.brunodles.validationbuilder.matcher.Common._if;

/**
 * Created by bruno on 03/06/16.
 */
public class StringMatcher {
    private String value;
    private IntConsumer adder;

    StringMatcher(String value, IntConsumer adder) {
        this.value = value;
        this.adder = adder;
    }

    public StringMatcher isEmpty() {
        _if(() -> value.isEmpty(), adder, Errors.EMPTY);
        return this;
    }

    public StringMatcher isNull() {
        _if(() -> value == null, adder, Errors.NULL);
        return this;
    }

    public StringMatcher lenght(Consumer<IntegerMatcher> matcher) {
        if (value != null)
            matcher.accept(new IntegerMatcher(value.length(), adder));
        return this;
    }
}
