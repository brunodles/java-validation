package com.github.brunodles.validationbuilder.matcher;

/**
 * Created by bruno on 03/06/16.
 */
public interface NumberMatcher<T extends Number, SubClass extends NumberMatcher> {

    SubClass isEqualsTo(T expected);

    SubClass isGreater(T expected);

    SubClass isLower(T expected);

    SubClass isBetween(T min, T max);
}
