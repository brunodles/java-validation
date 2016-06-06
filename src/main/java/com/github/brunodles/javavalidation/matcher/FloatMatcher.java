package com.github.brunodles.javavalidation.matcher;

import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class FloatMatcher extends NumberMatcherImpl<Float, FloatMatcher> {

    public FloatMatcher(Float value, IntConsumer adder) {
        super(value, adder);
    }
}
