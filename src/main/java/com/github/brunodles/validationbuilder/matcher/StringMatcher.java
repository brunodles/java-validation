package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.validationbuilder.Errors;

import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
public class StringMatcher {
    private String s;

    StringMatcher(String s) {
        this.s = s;
    }

    public StringMatcher isEmpty(Runnable block) {
        _if(() -> s.isEmpty(), block);
        return this;
    }

    public StringMatcher isNull(Runnable block) {
        _if(() -> s == null, block);
        return this;
    }

    public StringMatcher minLength(int length, Runnable block) {
        _if(() -> s.length() < 8, block);
        return this;
    }

    private static void _if(BooleanSupplier condition, Runnable block) {
        try {
            if (condition.getAsBoolean()) block.run();
        } catch (Exception e) {
        }
    }
}
