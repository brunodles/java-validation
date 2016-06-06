package com.github.brunodles.javavalidation.matcher;

import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class IntegerMatcher extends NumberMatcherImpl<Integer, IntegerMatcher> {

    public IntegerMatcher(Integer value, IntConsumer adder) {
        super(value, adder);
    }
}
