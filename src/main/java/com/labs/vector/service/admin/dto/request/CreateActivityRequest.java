package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateActivityRequest {
    private Integer activityId;
    private String activityName;
    private Integer subjectId;  // Foreign key reference
    private String description;
    private String isActive;
}

