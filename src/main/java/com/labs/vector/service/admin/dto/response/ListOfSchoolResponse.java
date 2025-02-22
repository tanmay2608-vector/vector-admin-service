package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.School;
import lombok.Data;

import java.util.List;

@Data
public class ListOfSchoolResponse {
    private List<School> schoolList;
    private String errorMessage;
}
