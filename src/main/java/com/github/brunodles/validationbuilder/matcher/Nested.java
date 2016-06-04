package com.github.brunodles.validationbuilder.matcher;

import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

/**
 * Created by bruno on 04/06/16.
 */
public class Nested<T> {
    private IntConsumer adder;
    private T value;

    public Nested(IntConsumer adder, T object) {
        this.adder = adder;
        value = object;
    }

    public IntegerMatcher when(Function<T, Integer> i) {
        return new When(adder).when(i.apply(value));
    }
}
