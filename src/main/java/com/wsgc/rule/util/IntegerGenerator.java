package com.wsgc.rule.util;


public class IntegerGenerator {
    private static int current;

    public IntegerGenerator() {
        this.current = 6000;
    }

    public static int next() {
        return current++;
    }
}

