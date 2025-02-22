package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.University;
import lombok.Data;

import java.util.List;

@Data
public class ListOfUniversityResponse {
    private List<University> universities;
    private String errorMessage;
}
