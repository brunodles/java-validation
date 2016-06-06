package com.github.brunodles.javavalidation.matcher;

import com.github.brunodles.javavalidation.Errors;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import static com.github.brunodles.javavalidation.matcher.Common._if;

/**
 * Created by bruno on 03/06/16.
 */
public class StringMatcher implements ObjectMatcher<String, StringMatcher>,
        EqualsMatcher<String, StringMatcher> {
    private String value;
    private IntConsumer adder;

    StringMatcher(String value, IntConsumer adder) {
        this.value = value;
        this.adder = adder;
    }

    /**
     * Check if the reference String is empty
     *
     * @return the current object, to be used as a builder
     */
    public StringMatcher isEmpty() {
        _if(() -> value.isEmpty(), adder, Errors.EMPTY);
        return this;
    }

    /**
     * Check if the reference String is null
     *
     * @return the current object, to be used as a builder
     */
    @Override
    public StringMatcher isNull() {
        _if(() -> value == null, adder, Errors.NULL);
        return this;
    }

    /**
     * Check if the length of the reference String is matching
     *
     * @param matcher a {@link IntegerMatcher} will passed as parameter to the Consumer
     * @return the current object, to be used as a builder
     */
    public StringMatcher length(Consumer<IntegerMatcher> matcher) {
        if (value != null)
            matcher.accept(new IntegerMatcher(value.length(), adder));
        return this;
    }

    /**
     * Check if the reference String is equals to expected
     *
     * @param expected another string to be compared
     * @return the current object, to be used as a builder
     */
    @Override
    public StringMatcher isEqualsTo(String expected) {
        _if(() -> value.equals(expected), adder, Errors.EQUAL);
        return this;
    }
}
