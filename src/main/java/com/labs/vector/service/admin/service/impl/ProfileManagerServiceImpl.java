package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.model.VectorRegisteredUser;
import com.labs.vector.service.admin.repository.VectorRegisteredUserRepository;
import com.labs.vector.service.admin.service.ProfileManagerService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import com.labs.vector.service.admin.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileManagerServiceImpl implements ProfileManagerService {

    @Autowired
    VectorRegisteredUserRepository vectorRegisteredUserRepository;

    @Override
    public ResponseEntity<?> getProfileDetails(Integer vectorUserID) {
        try {
            if(vectorUserID != null && vectorUserID > 0) {
                VectorRegisteredUser vectorUser = vectorRegisteredUserRepository.findById(vectorUserID)
                        .orElseThrow(() ->
                                new UserNotFoundException(
                                        "User not found for userID: " + vectorUserID, "404"
                                ));
                return ResponseEntity.ok(vectorUser);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"Profile not found", "No profile exists","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get Profile Details",e);
        }
    }
}
