package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateSchoolRequest {
    private Integer schoolId;
    private String schoolName;
    private String isActive;
    private String schoolAddress;
    private Integer academicYearID;
}

