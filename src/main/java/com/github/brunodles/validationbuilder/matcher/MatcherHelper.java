package com.github.brunodles.validationbuilder.matcher;

/**
 * Created by bruno on 03/06/16.
 */
public class MatcherHelper {

    public static StringMatcher when(String s){
        return new StringMatcher(s);
    }
}
