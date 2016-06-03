package com.github.brunodles.validationbuilder;

import com.github.brunodles.oleaster_suite_runner.OleasterSuiteRunner;
import org.junit.runner.RunWith;

import static com.github.brunodles.validationbuilder.Errors.*;
import static com.github.brunodles.validationbuilder.matcher.MatcherHelper.when;
import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

/**
 * Created by bruno on 03/06/16.
 */
@RunWith(OleasterSuiteRunner.class)
public class MainTest {

    private SampleValidator validator;

    private ValidationResult validation;

    {
        describe("Given a Validator", () -> {
            before(() -> {
                validator = new SampleValidator();
            });
            describe("when validate", () -> {
                describe("when pass a null value", () -> {
                    it("should throw NullPointerException", () -> {
                        expect(() -> validator.validate(null)).toThrow(NullPointerException.class);
                    });
                });
                describe("when pass a object with null values", () -> {
                    before(() -> {
                        validation = validator.validate(new SampleClass());
                    });
                    it("should be invalid", () -> {
                        expect(validation.isValid()).toBeFalse();
                    });
                    it("should add NULL name errors", () -> {
                        expect(validation.contains("name", NULL)).toBeTrue();
                    });
                    it("should not add EMPTY on name errors", () -> {
                        expect(validation.contains("name", EMPTY)).toBeFalse();
                    });
                    it("should not add MIN_LENGTH on name errors", () -> {
                        expect(validation.contains("name", MIN_LENGTH)).toBeFalse();
                    });
                });
                describe("when pass a object with empty values", () -> {
                    before(() -> {
                        SampleClass sample = new SampleClass();
                        sample.name = "";
                        validation = validator.validate(sample);
                    });
                    it("should be invalid", () -> {
                        expect(validation.isValid()).toBeFalse();
                    });
                    it("should not add NULL name errors", () -> {
                        expect(validation.contains("name", NULL)).toBeFalse();
                    });
                    it("should add EMPTY on name errors", () -> {
                        expect(validation.contains("name", EMPTY)).toBeTrue();
                    });
                    it("should add MIN_LENGTH on name errors", () -> {
                        expect(validation.contains("name", MIN_LENGTH)).toBeTrue();
                    });
                });
                describe("when pass a object with feel characters", () -> {
                    before(() -> {
                        SampleClass sample = new SampleClass();
                        sample.name = "123";
                        validation = validator.validate(sample);
                    });
                    it("should not add NULL name errors", () -> {
                        expect(validation.contains("name", NULL)).toBeFalse();
                    });
                    it("should add EMPTY on name errors", () -> {
                        expect(validation.contains("name", EMPTY)).toBeFalse();
                    });
                    it("should add MIN_LENGTH on name errors", () -> {
                        expect(validation.contains("name", MIN_LENGTH)).toBeTrue();
                    });
                });
            });
        });
    }


    private static class SampleClass {
        String name;
    }

    private static class SampleValidator implements Validator<SampleClass> {

        @Override
        public ValidationResult validate(SampleClass object) {
            ValidationResultBuilder errors = new ValidationResultBuilder();
            when(object.name)
                    .isNull(() -> errors.add("name", NULL))
                    .isEmpty(() -> errors.add("name", EMPTY))
                    .minLength(8, () -> errors.add("name", MIN_LENGTH));
            return errors;
        }
    }
}