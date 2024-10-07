package com.wavemaker.collections.exception;

public class IndexOutOfBoundException extends RuntimeException {
    private String message;

    public IndexOutOfBoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
