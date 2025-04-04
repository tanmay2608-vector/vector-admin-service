package com.labs.vector.service.admin.dto.request;
import lombok.Data;

@Data
public class CreateAcademicYearRequest {
    private Integer academicYearID;
    private String year;
    private String isActive;
}
