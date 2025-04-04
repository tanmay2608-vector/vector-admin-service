package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateSemesterRequest {
    private Integer semesterId;
    private String semesterName;
    private Integer courseId;  // Foreign key reference
    private String duration;
    private String isActive;
}

