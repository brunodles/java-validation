package com.github.brunodles.validationbuilder;

/**
 * Just a contant list of the errors
 * Created by bruno on 03/06/16.
 */
public class Errors {
    // Object
    public static final int NULL = 1;
    public static final int EQUAL = 2;

    // Boolean
    public static final int FALSE = 4;
    public static final int TRUE = 8;

    // String
    public static final int EMPTY = 16;

    // Integer
    public static final int GREATER = 32;
    public static final int LOWER = 64;
    public static final int BETWEEN = 128;
}
