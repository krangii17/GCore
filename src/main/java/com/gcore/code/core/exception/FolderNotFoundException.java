package com.gcore.code.core.exception;

public class FolderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7306451566196558536L;

    public FolderNotFoundException() {
        super();
    }

    public FolderNotFoundException(String s) {
        super(s);
    }
}
