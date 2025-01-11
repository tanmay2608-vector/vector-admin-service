package com.labs.vector.service.admin.utils;

import com.labs.vector.service.admin.dto.response.VectorSuccessResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@Builder
public class ResponseUtil {
    public static ResponseEntity<VectorSuccessResponse> createSuccessResponse(HttpStatus statusCode, Object message){
        //creating success detail obj...
        SuccessDetail successDetail = SuccessDetail.builder()
                .message(message)
                .statusCode(statusCode)
                .build();
        //Creating VectorSuccessResponse Obj...

        VectorSuccessResponse vectorSuccessResponse = VectorSuccessResponse.builder()
                .successResponse(List.of(successDetail))
                .build();

        return ResponseEntity.ok(vectorSuccessResponse);
    }

    public static ResponseEntity<?> createErrorResponse(HttpStatus statusCode, String message, String error, String description){

        ErrorDetail errorDetail = ErrorDetail.builder()
                .statusCode(statusCode)
                .message(message)
                .error(error)
                .description(description)
                .build();
        return ResponseEntity.badRequest().body(errorDetail);
    }
}
