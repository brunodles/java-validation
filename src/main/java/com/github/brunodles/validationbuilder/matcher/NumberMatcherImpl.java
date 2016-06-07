package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.retrofunctions.IntConsumer;
import com.github.brunodles.validationbuilder.Errors;

import static com.github.brunodles.validationbuilder.matcher.Common._if;

/**
 * Created by bruno on 03/06/16.
 */
class NumberMatcherImpl<T extends Number & Comparable,
        SubClass extends NumberMatcherImpl>
        implements NumberMatcher<T, SubClass>, ObjectMatcher<T, SubClass> {
    private T value;
    private IntConsumer adder;

    public NumberMatcherImpl(T value, IntConsumer adder) {
        this.value = value;
        this.adder = adder;
    }

    @Override
    public SubClass isEqualsTo(T expected) {
        _if(() -> value.equals(expected), adder, Errors.EQUAL);
        return (SubClass) this;
    }

    @Override
    public SubClass isGreater(T expected) {
        _if(() -> gt(expected), adder, Errors.GREATER);
        return (SubClass) this;
    }

    @Override
    public SubClass isLower(T expected) {
        _if(() -> lt(expected), adder, Errors.LOWER);
        return (SubClass) this;
    }

    @Override
    public SubClass isBetween(T min, T max) {
        _if(() -> bt(min, max), adder, Errors.BETWEEN);
        return (SubClass) this;
    }

    public boolean gt(T expected) {
        return value.compareTo(expected) > 0;
    }

    public boolean lt(T expected) {
        return value.compareTo(expected) < 0;
    }

    private boolean bt(T min, T max) {
        return gt(min) && lt(max);
    }

    @Override
    public SubClass isNull() {
        _if(() -> value == null, adder, Errors.NULL);
        return (SubClass) this;
    }
}
