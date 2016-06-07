package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.retrofunctions.IntConsumer;
import com.mscharhag.oleaster.runner.Invokable;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.mockito.Mockito.*;

/**
 * Created by bruno on 03/06/16.
 */
//@RunWith(OleasterSuiteRunner.class)
public abstract class NumberMatcherTestBase<T extends Number, Y extends NumberMatcher<T, ?>> {

    protected Y matcher;
    protected IntConsumer adder;

    protected void when_isEqualTo(T i, Invokable block) {
        describe("when param is " + i, () -> {
            before(() -> matcher.isEqualsTo(i));
            block.invoke();
        });
    }

    protected void when_isGreater(T i, Invokable block) {
        describe("when param is " + i, () -> {
            before(() -> matcher.isGreater(i));
            block.invoke();
        });
    }

    protected void when_isLower(T i, Invokable block) {
        describe("when param is " + i, () -> {
            before(() -> matcher.isLower(i));
            block.invoke();
        });
    }

    protected void when_isBetween(T i, T j, Invokable block) {
        describe("when param is " + i + " and " + j, () -> {
            before(() -> matcher.isBetween(i, j));
            block.invoke();
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