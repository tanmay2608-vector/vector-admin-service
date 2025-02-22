package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.utils.ErrorDetail;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class ErrorResponse {
    public List<ErrorDetail> errors;
}
