package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateUserRequest;
import com.labs.vector.service.admin.dto.response.VectorSuccessResponse;
import com.labs.vector.service.admin.model.VectorRegisteredUser;
import com.labs.vector.service.admin.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/vector-service/v1/registration")
public class UserRegistrationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/createUser")
    public ResponseEntity<?> crreateUser(@RequestBody CreateUserRequest createUserRequest){
        return null;
    }

    @PostMapping("/generateToken")
    public String token(@RequestBody VectorRegisteredUser vectorRegisteredUser){
        try {
            //validating userName  by using authentication Manager....
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(vectorRegisteredUser.getUserName(),vectorRegisteredUser.getPassword()));
            return JWTUtil.generateAccessToken(vectorRegisteredUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Something Went Wrong...";
    }
}
