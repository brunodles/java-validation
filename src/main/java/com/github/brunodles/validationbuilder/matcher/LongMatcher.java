package com.github.brunodles.validationbuilder.matcher;

import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class LongMatcher extends NumberMatcherImpl<Long, LongMatcher> {
    public LongMatcher(Long value, IntConsumer adder) {
        super(value, adder);
    }
}
