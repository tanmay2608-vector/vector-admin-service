package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.UserDetailsEntity;
import com.labs.vector.service.admin.model.VectorRegisteredUser;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ListOfUserResponse {
    private List<VectorRegisteredUser> vectorAdminusers;
    private List<UserDetailsEntity> vectorUsers;
    private String errorMessage;
}
