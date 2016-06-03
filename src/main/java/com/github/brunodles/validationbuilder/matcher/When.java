package com.github.brunodles.validationbuilder.matcher;

import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class When {

    private IntConsumer adder;

    public When(IntConsumer adder) {
        this.adder = adder;
    }

    public StringMatcher when(String s) {
        return new StringMatcher(s, adder);
    }
}
