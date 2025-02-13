package com.labs.vector.service.admin.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenResponse {
    private String accessToken;
    private String refreshToken;
}
