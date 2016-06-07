package com.github.brunodles.javavalidation.matcher;

import com.github.brunodles.javavalidation.Errors;
import com.github.brunodles.retrofunctions.IntConsumer;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by bruno on 04/06/16.
 */
@RunWith(OleasterRunner.class)
public class BooleanMatcherTest {

    private BooleanMatcher matcher;
    private Boolean param;
    private IntConsumer adder;

    {
        describe("given a BooleanMatcher", () -> {
            beforeEach(() -> {
                adder = mock(IntConsumer.class);
                matcher = new BooleanMatcher(param, adder);
            });
            describe("when param is false", () -> {
                before(() -> param = false);
                describe("when call isTrue", () -> {
                    beforeEach(() -> matcher.isTrue());
                    itShouldNotCallAdder();
                });
                describe("when call isFalse", () -> {
                    beforeEach(() -> matcher.isFalse());
                    itShouldCallAdderWith("FALSE", Errors.FALSE);
                });
            });
            describe("when param is true", () -> {
                before(() -> param = true);
                describe("when call isTrue", () -> {
                    beforeEach(() -> matcher.isTrue());
                    itShouldCallAdderWith("TRUE", Errors.TRUE);
                });
                describe("when call isFalse", () -> {
                    beforeEach(() -> matcher.isFalse());
                    itShouldNotCallAdder();
                });
            });
        });
    }

    protected void itShouldCallAdderWith(final String name, int value) {
        it("should call adder to add " + name + " in errors", () -> {
            verify(adder).accept(eq(value));
        });
    }

    protected void itShouldNotCallAdder() {
        it("should not call adder", () -> {
            verifyZeroInteractions(adder);
        });
    }

}