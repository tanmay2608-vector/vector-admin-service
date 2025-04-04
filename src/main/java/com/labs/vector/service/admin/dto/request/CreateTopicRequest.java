package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateTopicRequest {
    private Integer topicId;
    private String topicName;
    private Integer activityId;  // Foreign key reference
    private String isActive;
}

