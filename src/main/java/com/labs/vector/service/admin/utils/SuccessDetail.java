package com.labs.vector.service.admin.utils;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SuccessDetail {
    private Object message;
    private HttpStatus statusCode;
}
