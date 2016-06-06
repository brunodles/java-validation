package com.github.brunodles.javavalidation.matcher;

import com.github.brunodles.javavalidation.Errors;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.util.function.IntConsumer;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.before;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static org.mockito.Mockito.mock;

/**
 * Created by bruno on 04/06/16.
 */
@RunWith(OleasterRunner.class)
public class IntegerMatcherTest extends NumberMatcherTestBase<Integer, IntegerMatcher> {

    {
        describe("Given a IntegerMatcher for 7", () -> {
            before(() -> {
                adder = mock(IntConsumer.class);
                matcher = new IntegerMatcher(7, adder);
            });
            describe("when call isBetween", () -> {
                when_isBetween(1, 7, this::itShouldNotCallAdder);
                when_isBetween(7, 10, this::itShouldNotCallAdder);
                when_isBetween(5, 9, () -> itShouldCallAdderWith("BETWEEN", Errors.BETWEEN));
            });
            describe("when call isLower", () -> {
                when_isLower(7, this::itShouldNotCallAdder);
                when_isLower(8, () -> itShouldCallAdderWith("LOWER", Errors.LOWER));
            });
            describe("when call isGreater", () -> {
                when_isGreater(7, this::itShouldNotCallAdder);
                when_isGreater(6, () -> itShouldCallAdderWith("GREATER", Errors.GREATER));
            });
            describe("when call isEqual", () -> {
                when_isEqualTo(6, this::itShouldNotCallAdder);
                when_isEqualTo(8, this::itShouldNotCallAdder);
                when_isEqualTo(7, () -> itShouldCallAdderWith("EQUAL", Errors.EQUAL));
            });
        });
    }
}
