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
public class FloatMatcherTest extends NumberMatcherTestBase<Float, FloatMatcher> {

    {
        describe("Given a IntegerMatcher for 7", () -> {
            before(() -> {
                adder = mock(IntConsumer.class);
                matcher = new FloatMatcher(7F, adder);
            });
            describe("when call isBetween", () -> {
                when_isBetween(1F, 7F, this::itShouldNotCallAdder);
                when_isBetween(7F, 10F, this::itShouldNotCallAdder);
                when_isBetween(5F, 9F, () -> itShouldCallAdderWith("BETWEEN", Errors.BETWEEN));
            });
            describe("when call isLower", () -> {
                when_isLower(7F, this::itShouldNotCallAdder);
                when_isLower(8F, () -> itShouldCallAdderWith("LOWER", Errors.LOWER));
            });
            describe("when call isGreater", () -> {
                when_isGreater(7F, this::itShouldNotCallAdder);
                when_isGreater(6F, () -> itShouldCallAdderWith("GREATER", Errors.GREATER));
            });
            describe("when call isEqual", () -> {
                when_isEqualTo(6F, this::itShouldNotCallAdder);
                when_isEqualTo(8F, this::itShouldNotCallAdder);
                when_isEqualTo(7F, () -> itShouldCallAdderWith("EQUAL", Errors.EQUAL));
            });
        });
    }
}
