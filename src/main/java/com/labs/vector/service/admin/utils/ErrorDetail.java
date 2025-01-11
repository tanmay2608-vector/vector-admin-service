package com.labs.vector.service.admin.utils;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDetail {
    private HttpStatus statusCode;
    private String message;
    private String description;
    private String error;

    @Builder.Default
    private LocalDateTime localDateTime = LocalDateTime.now();
}
