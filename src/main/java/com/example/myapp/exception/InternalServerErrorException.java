package com.example.myapp.exception;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }
}
