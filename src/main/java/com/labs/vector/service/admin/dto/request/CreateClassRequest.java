package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateClassRequest {
    private Integer classId;
    private String className;
    private String isActive;
}

