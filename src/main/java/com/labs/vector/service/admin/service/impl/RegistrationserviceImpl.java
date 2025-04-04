package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateUserRequest;
import com.labs.vector.service.admin.dto.response.ListOfUserResponse;
import com.labs.vector.service.admin.dto.response.VectorSuccessResponse;
import com.labs.vector.service.admin.enums.Gender;
import com.labs.vector.service.admin.model.UserDetailsEntity;
import com.labs.vector.service.admin.model.UserRoleMasterMap;
import com.labs.vector.service.admin.model.VectorRegisteredUser;
import com.labs.vector.service.admin.repository.UserDetailsRepository;
import com.labs.vector.service.admin.repository.UserRoleMasterMapRepository;
import com.labs.vector.service.admin.repository.VectorRegisteredUserRepository;
import com.labs.vector.service.admin.service.RegistrationService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import com.labs.vector.service.admin.utils.exceptions.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationserviceImpl implements RegistrationService {
    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    @Autowired
    VectorRegisteredUserRepository vectorRegisteredUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRoleMasterMapRepository userRoleMasterMapRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public ResponseEntity<?> createVectorUser(CreateUserRequest createUserRequest) {
        try {
            // Check if the userName already exists in the database
            log.info("test data");
            Optional<VectorRegisteredUser> vectorRegisteredUser = vectorRegisteredUserRepository.findByUserName(createUserRequest.getUserName());
            if (vectorRegisteredUser.isPresent() && vectorRegisteredUser.get() != null) {
                // Return error response if the username is already taken
                return ResponseUtil.createErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        "Data duplicates",
                        "ERROR: User Name already exists",
                        "Please choose a different username."
                );
            }
            VectorRegisteredUser vectorUser = new VectorRegisteredUser();
            log.info("Vector User : {}", vectorUser.toString());

            //Updating user details of user is already exist...
            if(createUserRequest.getUserID() != null && createUserRequest.getUserID() > 0){
                Optional<VectorRegisteredUser> existingUser = vectorRegisteredUserRepository.findById(createUserRequest.getUserID());
                if (vectorRegisteredUser.isPresent() && existingUser.get() != null){
                    vectorUser = existingUser.get();
                }
            }

            // Create new user object and save it
            vectorUser.setUserName(createUserRequest.getUserName());
            vectorUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            vectorUser.setFullName(createUserRequest.getFullName());
            vectorUser.setUserEmail(createUserRequest.getEmailId());
            vectorUser.setPhoneNumber(createUserRequest.getPhoneNumber());
            vectorUser.setGender(createUserRequest.getGender());
            vectorUser.setNamePrefix(createUserRequest.getNamePrefix());
            vectorUser.setUserNameAlias(createUserRequest.getUserNameAlias());
            vectorUser.setDob(createUserRequest.getDob());
            vectorUser.setAge(createUserRequest.getAge());
            vectorUser.setDateOfJoining(createUserRequest.getDateOfJoining());
            vectorUser.setEmailId(createUserRequest.getEmailId());
            vectorUser.setCountryCode(createUserRequest.getCountryCode());
            vectorUser.setWhatsappCountryCode(createUserRequest.getWhatsappCountryCode());
            vectorUser.setPhoneNumber(createUserRequest.getPhoneNumber());
            vectorUser.setEmergencyNumber(createUserRequest.getEmergencyNumber());
            vectorUser.setEmergencyContactPerson(createUserRequest.getEmergencyContactPerson());
            vectorUser.setSecondaryPhoneNumber(createUserRequest.getSecondaryPhoneNumber());
            vectorUser.setWhatsappNumber(createUserRequest.getWhatsappNumber());
            vectorUser.setGender(createUserRequest.getGender());
            vectorUser.setOfficialEmailId(createUserRequest.getOfficialEmailId());
            vectorUser.setUserCode(createUserRequest.getUserCode());
            vectorUser.setStatus(createUserRequest.getStatus());
            vectorUser.setUserType(createUserRequest.getUserType());
            vectorUser.setDepartmentName(createUserRequest.getDepartmentName());
            vectorUser.setDesignation(createUserRequest.getDesignation());
            vectorUser.setCreatedByID(createUserRequest.getCreatedByID());
            vectorUser.setCreatedByName(createUserRequest.getCreatedByName());
            vectorUser.setUpdatedByID(createUserRequest.getUpdatedByID());
            vectorUser.setUpdatedByName(createUserRequest.getUpdatedByName());
            vectorUser.setUpdatedTime(LocalDateTime.now()); // Setting current timestamp

            // Save the new user
            vectorRegisteredUserRepository.save(vectorUser);
            //Managing role....
            if(createUserRequest.getVectorRoleIDList() != null && createUserRequest.getVectorRoleIDList().size() > 0){
                //clearing previous entries...
                userRoleMasterMapRepository.deleteByUserID(vectorUser.getVectorUserID());

                for(Integer id : createUserRequest.getVectorRoleIDList()){
                    UserRoleMasterMap userRoleMasterMap = new UserRoleMasterMap();
                    userRoleMasterMap.setUserID(vectorUser.getVectorUserID());
                    userRoleMasterMap.setRoleMaterID(id);
                    userRoleMasterMapRepository.save(userRoleMasterMap);
                }
            }

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
                log.info("vector Register User is Present");
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

    @Override
    public ResponseEntity<?> getRegisteredVectorUsers(Integer userID) {

        VectorRegisteredUser vectorAdminUser = vectorRegisteredUserRepository.findById(userID)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with userId: " + userID,
                                "404"
                        ));
        log.info("Vector Admin User : {}", vectorAdminUser.toString());

        List<VectorRegisteredUser> vectorAdminUsers = vectorRegisteredUserRepository.findAll();
        List<UserDetailsEntity> vectorUsers = userDetailsRepository.findAll();
        ListOfUserResponse listUser = new ListOfUserResponse();
        listUser.setVectorAdminusers(vectorAdminUsers);
        listUser.setVectorUsers(vectorUsers);

        log.info("Users List :{}", vectorUsers.toString());
        return ResponseEntity.ok(listUser);
    }

    @Override
    public ResponseEntity<?> getVectorAdminUserByID(Integer userID) {
        try{
            VectorRegisteredUser vectorAdminUser = vectorRegisteredUserRepository.findById(userID)
                    .orElseThrow(() ->
                            new UserNotFoundException(
                                    "User not found with userId: " + userID,
                                    "404"
                            ));
            log.info("Vector Admin User By Id : {}", vectorAdminUser.toString());
        return ResponseEntity.ok(vectorAdminUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get Vector Admin User By ID ",e);
        }
    }

    @Override
    public ResponseEntity<?> getVectorUserByID(Integer userID) {
        try{
            UserDetailsEntity vectorUser = userDetailsRepository.findById(userID)
                    .orElseThrow(() ->
                            new UserNotFoundException(
                                    "User not found with userId: " + userID,
                                    "404"
                            ));
            log.info("User :{}",vectorUser.toString());
            return ResponseEntity.ok(vectorUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get Vector user User By ID ",e);
        }
    }

}
