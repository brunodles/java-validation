package com.github.brunodles.validationbuilder.matcher;

import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class DoubleMatcher extends NumberMatcherImpl<Double, DoubleMatcher> {
    public DoubleMatcher(Double value, IntConsumer adder) {
        super(value, adder);
    }
}
