package com.labs.vector.service.admin.utils.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    private final String statusCode;

    public UserAlreadyExistsException(String message, String statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
