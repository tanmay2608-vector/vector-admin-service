package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateGoalRequest {
    private Integer goalId;
    private String goalName;
    private String isActive;
}

