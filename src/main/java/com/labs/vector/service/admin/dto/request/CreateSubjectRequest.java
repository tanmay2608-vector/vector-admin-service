package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateSubjectRequest {
    private Integer subjectId;
    private String subjectName;
    private String isActive;
}
