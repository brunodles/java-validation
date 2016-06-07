package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.retrofunctions.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class FloatMatcher extends NumberMatcherImpl<Float, FloatMatcher> {

    public FloatMatcher(Float value, IntConsumer adder) {
        super(value, adder);
    }
}
