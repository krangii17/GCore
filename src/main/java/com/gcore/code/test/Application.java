package com.gcore.code.test;

import com.gcore.code.core.parser.PropertiesParse;

public class Application {
    public static void main(String[] args) {
        PropertiesParse parse = new PropertiesParse();
        System.out.println(parse.getFolder());
    }
}
