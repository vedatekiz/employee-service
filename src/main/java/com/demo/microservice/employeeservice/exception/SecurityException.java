package com.demo.microservice.employeeservice.exception;

import org.springframework.http.HttpStatus;

public class SecurityException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public SecurityException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
