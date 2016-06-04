package com.github.brunodles.validationbuilder.matcher;

/**
 * Created by bruno on 03/06/16.
 */
public interface NumberMatcher<T extends Number, S extends NumberMatcher> {

    S isEqualsTo(T expected);

    S isGreater(T expected);

    S isLower(T expected);

    S isBetween(T min, T max);
}
