package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateUserRequest;
import com.labs.vector.service.admin.dto.response.VectorSuccessResponse;
import com.labs.vector.service.admin.model.VectorRegisteredUser;
import com.labs.vector.service.admin.repository.VectorRegisteredUserRepository;
import com.labs.vector.service.admin.service.RegistrationService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationserviceImpl implements RegistrationService {

    @Autowired
    VectorRegisteredUserRepository vectorRegisteredUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createVectorUser(CreateUserRequest createUserRequest) {
        try {
            // Check if the userName already exists in the database
            Optional<VectorRegisteredUser> vectorRegisteredUser = vectorRegisteredUserRepository.findByUserName(createUserRequest.getUserName());
            if (vectorRegisteredUser.isPresent()) {
                // Return error response if the username is already taken
                return ResponseUtil.createErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        "Data duplicates",
                        "ERROR: User Name already exists",
                        "Please choose a different username."
                );
            }

            // Create new user object and save it
            VectorRegisteredUser vectorUser = new VectorRegisteredUser();
            vectorUser.setUserName(createUserRequest.getUserName());
            vectorUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            vectorUser.setFullName(createUserRequest.getFullName());

            // Save the new user
            vectorRegisteredUserRepository.save(vectorUser);

            // Return success response after creating the user
            return ResponseUtil.createSuccessResponse(
                    HttpStatus.CREATED,
                    vectorUser
            );

        } catch (Exception e) {
            // Handle any unexpected errors and return a generic error response
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred",
                    "ERROR: An error occurred while processing your request.",
                    e.getMessage()
            );
        }
    }
    @Override
    public ResponseEntity<?> getLoggedUser(CreateUserRequest createLoggedUserRequest){
        try {
            Optional<VectorRegisteredUser> vectorRegisteredUser = vectorRegisteredUserRepository.findByUserNameAndPassword(
                    createLoggedUserRequest.getUserName(),
                    createLoggedUserRequest.getPassword());

            if (vectorRegisteredUser.isPresent()) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, vectorRegisteredUser);
            }else {
                return ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,
                        "No records found",
                        "ERROR: Invalid Credentials",
                        "Please verify username and password");
            }
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                "ERROR: An error occurred while processing your request.",
                e.getMessage()
            );
        }
    }

}
