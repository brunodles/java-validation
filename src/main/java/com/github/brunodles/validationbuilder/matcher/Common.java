package com.github.brunodles.validationbuilder.matcher;

import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

/**
 * Created by bruno on 03/06/16.
 */
class Common {

    static void _if(BooleanSupplier condition, IntConsumer block, int error) {
        try {
            if (condition.getAsBoolean()) block.accept(error);
        } catch (Exception e) {
        }
    }
}
