package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.retrofunctions.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class LongMatcher extends NumberMatcherImpl<Long, LongMatcher> {
    public LongMatcher(Long value, IntConsumer adder) {
        super(value, adder);
    }
}
