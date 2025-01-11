package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.utils.SuccessDetail;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class VectorSuccessResponse {
    List<SuccessDetail> successResponse;
}
