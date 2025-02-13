package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateUserRequest;
import com.labs.vector.service.admin.dto.response.VectorSuccessResponse;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {

    public ResponseEntity<?> createVectorUser(CreateUserRequest createUserRequest);
    public ResponseEntity<?> getLoggedUser(CreateUserRequest loggedUserDetails);
    public ResponseEntity<?> getRegisteredVectorUsers(Integer userID);
    public ResponseEntity<?> getVectorAdminUserByID(Integer userID);
    public ResponseEntity<?> getVectorUserByID(Integer userID);
}
