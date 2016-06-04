package com.github.brunodles.validationbuilder;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.github.brunodles.validationbuilder.Errors.*;
import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

/**
 * Created by bruno on 03/06/16.
 */
@RunWith(OleasterRunner.class)
public class MainTest {

    private SampleValidator validator;

    private ValidationResult validation;

    {
        describe("Given a Validator", () -> {
            before(() -> {
                validator = new SampleValidator();
            });

            describe("when pass a null value", () -> {
                it("should throw NullPointerException", () -> {
                    expect(() -> validator.validate(null)).toThrow(NullPointerException.class);
                });
            });

            describe("when validate a String field", () -> {

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
                    it("should not add LOWER on name errors", () -> {
                        expect(validation.contains("name", LOWER)).toBeFalse();
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
                    it("should add LOWER on name errors", () -> {
                        expect(validation.contains("name", LOWER)).toBeTrue();
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
                    it("should add LOWER on name errors", () -> {
                        expect(validation.contains("name", LOWER)).toBeTrue();
                    });
                });
            });

            describe("when validate a int field", () -> {
                before(() -> {
                    SampleClass sample = new SampleClass();
                    validation = validator.validate(sample);
                });
                it("should not add NULL on strikeCount errors", () -> {
                    expect(validation.contains("strikeCount", NULL)).toBeFalse();
                });

                describe("when contains the wrong value", () -> {
                    before(() -> {
                        SampleClass sample = new SampleClass();
                        sample.strikeCount = 7;
                        validation = validator.validate(sample);
                    });
                    it("should add GREATER on strikeCount errors", () -> {
                        expect(validation.contains("strikeCount", GREATER)).toBeTrue();
                    });
                });

            });

        });

    }


    private static class SampleClass {
        String name;
        int strikeCount;
    }

    private static class SampleValidator extends ValidatorBase<SampleClass> {
        @Override
        void validate(SampleClass object, ValidationResultBuilder builder) {
            builder.addTo("name").when(object.name).isNull().isEmpty().lenght(i -> i.isLower(8));
            builder.addTo("strikeCount").when(object.strikeCount).isNull().isGreater(6);
        }
    }
}