package com.gcore.code.core.exception;

public class PropertiesNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5213445540555840583L;

    public PropertiesNotFoundException() {
        super();
    }

    public PropertiesNotFoundException(String s) {
        super(s);
    }
}
