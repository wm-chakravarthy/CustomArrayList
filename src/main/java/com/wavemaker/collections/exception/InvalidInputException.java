package com.wavemaker.collections.exception;

public class InvalidInputException extends RuntimeException{
    private String message;

    public InvalidInputException(String message) {
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
