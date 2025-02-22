package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateUniversityRequest {
    private Integer universityId;
    private String universityName;
    private String isActive;
}

