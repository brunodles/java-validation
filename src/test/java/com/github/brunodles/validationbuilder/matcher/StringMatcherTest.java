package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.validationbuilder.Errors;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.util.function.IntConsumer;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by bruno on 04/06/16.
 */
@RunWith(OleasterRunner.class)
public class StringMatcherTest {

    private IntConsumer adder;

    private StringMatcher matcher;

    private String param;

    {
        describe("Given a StringMatcher", () -> {
            beforeEach(() -> {
                adder = mock(IntConsumer.class);
                matcher = new StringMatcher(param, adder);
            });
            describe("when param is null", () -> {
                before(() -> param = null);
                describe("when call isEmpty", () -> {
                    beforeEach(() -> matcher.isEmpty());
                    itShouldNotCallAdder();
                });
                describe("when call isNull", () -> {
                    beforeEach(() -> matcher.isNull());
                    itShouldCallAdderWith("NULL", Errors.NULL);
                });
            });
            describe("when param is empty", () -> {
                before(() -> param = "");
                describe("when call isEmpty", () -> {
                    beforeEach(() -> matcher.isEmpty());
                    itShouldCallAdderWith("EMPTY", Errors.EMPTY);
                });
                describe("when call isEqualTo another empty String", () -> {
                    beforeEach(() -> matcher.isEqualsTo(""));
                    itShouldCallAdderWith("EQUAL", Errors.EQUAL);
                });
                describe("when call isNull", () -> {
                    beforeEach(() -> matcher.isNull());
                    itShouldNotCallAdder();
                });
                describe("when call Lenght isEqualTo 0", () -> {
                    beforeEach(() -> matcher.lenght(l -> l.isEqualsTo(0)));
                    itShouldCallAdderWith("EQUAL", Errors.EQUAL);
                });
            });
            describe("when param is 'Anakin SkyWalker'", () -> {
                before(() -> param = "Anakin SkyWalker");
                describe("when call isEmpty", () -> {
                    beforeEach(() -> matcher.isEmpty());
                    itShouldNotCallAdder();
                });
                describe("when call isEqual with another String", () -> {
                    beforeEach(() -> matcher.isEqualsTo("Anakin SkiUalker"));
                    itShouldNotCallAdder();
                });
                describe("when call isEqual with a equal String", () -> {
                    beforeEach(() -> matcher.isEqualsTo("Anakin SkyWalker"));
                    itShouldCallAdderWith("EQUAL", Errors.EQUAL);
                });
                describe("when call isNull", () -> {
                    beforeEach(() -> matcher.isNull());
                    itShouldNotCallAdder();
                });
                describe("when call Lenght isEqualTo 16", () -> {
                    beforeEach(() -> matcher.lenght(l -> l.isEqualsTo(16)));
                    itShouldCallAdderWith("EQUAL", Errors.EQUAL);
                });
                describe("when call Lenght isLower 16", () -> {
                    beforeEach(() -> matcher.lenght(l -> l.isLower(16)));
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