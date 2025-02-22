package com.labs.vector.service.admin.utils.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends RuntimeException{
    private final HttpStatus statusCode;
    public HttpStatus getStatusCode() {
        return statusCode;
    }
    UnauthorizedAccessException(HttpStatus statusCode, String message){
        super(message);
        this.statusCode = statusCode;
    }
}
