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
public class ObjectMatcherTest {

    private ObjectMatcherImpl<SampleClass> matcher;

    private IntConsumer adder;

    private SampleClass param;

    {
        describe("Given a ObjectRunner", () -> {
            beforeEach(() -> {
                adder = mock(IntConsumer.class);
                matcher = new ObjectMatcherImpl<SampleClass>(param, adder);
            });
            describe("when param is null", () -> {
                before(() -> param = null);
                describe("when call isNull", () -> {
                    beforeEach(() -> matcher.isNull());
                    itShouldCallAdderWith("NULL", Errors.NULL);
                });
                describe("when call isEquals with another object", () -> {
                    beforeEach(() -> matcher.isEqualsTo(new SampleClass()));
                    itShouldNotCallAdder();
                });
            });
            describe("When param is a Object", () -> {
                before(() -> param = new SampleClass());
                describe("when call isNull", () -> {
                    beforeEach(() -> matcher.isNull());
                    itShouldNotCallAdder();
                });
                describe("when call isEquals with a diferent object", () -> {
                    beforeEach(() -> matcher.isEqualsTo(new SampleClass()));
                    itShouldCallAdderWith("EQUALS", Errors.EQUAL);
                });
            });
        });
    }

    public static class SampleClass {

        private boolean equal;

        public SampleClass() {
        }

        public SampleClass(boolean equal) {
            this.equal = equal;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SampleClass that = (SampleClass) o;

            return equal == that.equal;

        }

        @Override
        public int hashCode() {
            return (equal ? 1 : 0);
        }
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