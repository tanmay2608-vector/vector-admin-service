package com.labs.vector.service.admin.utils.exceptions;

public class UserNotFoundException extends RuntimeException{
    private final String statusCode;

    public UserNotFoundException(String message, String statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
