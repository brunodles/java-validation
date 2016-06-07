package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.retrofunctions.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class DoubleMatcher extends NumberMatcherImpl<Double, DoubleMatcher> {
    public DoubleMatcher(Double value, IntConsumer adder) {
        super(value, adder);
    }
}
