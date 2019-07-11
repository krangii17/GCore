package com.gcore.code.core.contatiner.exception;

public class NoSuchBeanException extends RuntimeException {
    private static final long serialVersionUID = 7847911312314358140L;

    public NoSuchBeanException() {
        super();
    }

    public NoSuchBeanException(String s) {
        super(s);
    }
}
