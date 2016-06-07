package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.retrofunctions.IntConsumer;

/**
 * This will take the fields and return a matcher for it's value
 * Created by bruno on 03/06/16.
 */
public class When {

    private IntConsumer adder;

    public When(IntConsumer adder) {
        this.adder = adder;
    }

    /**
     * @param value a String to be the reference value
     * @return a {@link StringMatcher}
     */
    public StringMatcher when(String value) {
        return new StringMatcher(value, adder);
    }

    /**
     * @param value a Integer to be the reference value
     * @return a {@link IntegerMatcher}
     */
    public IntegerMatcher when(Integer value) {
        return new IntegerMatcher(value, adder);
    }

    /**
     * @param value a Long to be the reference value
     * @return a {@link LongMatcher}
     */
    public LongMatcher when(Long value) {
        return new LongMatcher(value, adder);
    }

    /**
     * @param value a Float to be the reference value
     * @return a {@link FloatMatcher}
     */
    public FloatMatcher when(Float value) {
        return new FloatMatcher(value, adder);
    }

    /**
     * @param value a Double to be the reference value
     * @return a {@link DoubleMatcher}
     */
    public DoubleMatcher when(Double value) {
        return new DoubleMatcher(value, adder);
    }

    /**
     * @param object a Object to be the reference value
     * @param <T>    Don't need to worry with this parameter it will be auto inferred by compiler
     * @return a {@link ObjectMatcher}
     */
    public <T> ObjectMatcher<T, ?> when(final T object) {
        return new ObjectMatcherImpl<T>(object, adder);
    }
}
