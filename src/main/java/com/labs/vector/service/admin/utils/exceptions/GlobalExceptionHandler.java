package com.labs.vector.service.admin.utils.exceptions;

import com.labs.vector.service.admin.dto.response.ErrorResponse;
import com.labs.vector.service.admin.utils.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccess(UnauthorizedAccessException ex){
        ErrorDetail errorDetail = ErrorDetail.builder()
                .error("Unauthorized")
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .description("Please provide a valid ;access token.")
                .build();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errors(List.of(errorDetail))
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        ErrorDetail errorDetail = ErrorDetail.builder()
                .error("Unhandled Exception")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An unexpected error occured: "+ex.getMessage())
                .description("Please contact support for assistance.")
                .build();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errors(List.of(errorDetail))
                .build();
        return new  ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException ex){
        ErrorDetail errorDetail = ErrorDetail.builder()
                .error("Unhandled Exception")
                .statusCode(HttpStatus.BAD_REQUEST)
                .message("An unexpected error occurred: " + ex.getMessage())
                .description("Please contact support for assistance.")
                .build();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errors(List.of(errorDetail))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .error("Internal Server Error")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .description("An unexpected error occurred.")
                .build();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errors(List.of(errorDetail))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
