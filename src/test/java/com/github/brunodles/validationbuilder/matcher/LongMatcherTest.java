package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.retrofunctions.IntConsumer;
import com.github.brunodles.validationbuilder.Errors;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.before;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static org.mockito.Mockito.mock;

/**
 * Created by bruno on 04/06/16.
 */
@RunWith(OleasterRunner.class)
public class LongMatcherTest extends NumberMatcherTestBase<Long, LongMatcher> {

    {
        describe("Given a IntegerMatcher for 7", () -> {
            before(() -> {
                adder = mock(IntConsumer.class);
                matcher = new LongMatcher(7L, adder);
            });
            describe("when call isBetween", () -> {
                when_isBetween(1L, 7L, this::itShouldNotCallAdder);
                when_isBetween(7L, 10L, this::itShouldNotCallAdder);
                when_isBetween(5L, 9L, () -> itShouldCallAdderWith("BETWEEN", Errors.BETWEEN));
            });
            describe("when call isLower", () -> {
                when_isLower(7L, this::itShouldNotCallAdder);
                when_isLower(8L, () -> itShouldCallAdderWith("LOWER", Errors.LOWER));
            });
            describe("when call isGreater", () -> {
                when_isGreater(7L, this::itShouldNotCallAdder);
                when_isGreater(6L, () -> itShouldCallAdderWith("GREATER", Errors.GREATER));
            });
            describe("when call isEqual", () -> {
                when_isEqualTo(6L, this::itShouldNotCallAdder);
                when_isEqualTo(8L, this::itShouldNotCallAdder);
                when_isEqualTo(7L, () -> itShouldCallAdderWith("EQUAL", Errors.EQUAL));
            });
        });
    }
}
