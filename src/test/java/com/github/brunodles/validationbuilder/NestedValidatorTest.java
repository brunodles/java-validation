package com.github.brunodles.validationbuilder;

import com.github.brunodles.oleaster_suite_runner.OleasterSuiteRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.beforeEach;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;

/**
 * Created by bruno on 04/06/16.
 */
@RunWith(OleasterSuiteRunner.class)
public class NestedValidatorTest {

    private SampleValidator validator;

    {
        describe("Given a Validator", () -> {
            beforeEach(() -> validator = new SampleValidator());
            describe("when ", () -> {

            });
        });
    }

    private static class NestedClass {
        String stringField;
        int intField;
    }

    private static class SampleClass {
        Long longField;
        NestedClass nestedObject;
    }

    private static class SampleValidator extends ValidatorBase<SampleClass> {

        @Override
        void validate(SampleClass object, ValidationResultBuilder b) {
            b.addTo("longField").when(object.longField).isEqualsTo(0L);
            b.addTo("nestedObject.intField").nested(object.nestedObject)
                    .when(o -> o.intField).isGreater(5);
            b.addTo("stringField").nested(() -> object.nestedObject.stringField)
                    .when(String.class)
        }
    }
}
