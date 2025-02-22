package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private Integer courseId;
    private String courseName;
    private Integer universityId;  // Foreign key reference
    private String isActive;
}

