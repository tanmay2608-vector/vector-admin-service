package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.UserDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfVectorAppUserResponse {
    private List<UserDetailsEntity> userDetailsEntities;
    private String errorMessage;
}
