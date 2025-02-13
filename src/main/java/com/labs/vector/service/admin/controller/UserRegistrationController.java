package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateUserRequest;
import com.labs.vector.service.admin.dto.response.JwtTokenResponse;
import com.labs.vector.service.admin.model.VectorRegisteredUser;
import com.labs.vector.service.admin.service.RegistrationService;
import com.labs.vector.service.admin.service.impl.UserDetailsServiceImp;
import com.labs.vector.service.admin.utils.JWTUtil;
import com.labs.vector.service.admin.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/admin/vector-service/v1/registration")
public class UserRegistrationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid  @RequestBody CreateUserRequest createUserRequest, BindingResult result){
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }

        return registrationService.createVectorUser(createUserRequest);
    }

    @PostMapping("/vector-user-login")
    public ResponseEntity<?> vectorUserLogin(@Valid @RequestBody VectorRegisteredUser vectorRegisteredUser, BindingResult result){
        try {

            if(result.hasErrors()){
                ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                        "Validation Error",
                        result.getFieldError().getDefaultMessage(),
                        "Please correct the input fields as per the validation rules.");
            }

            //validating userName by using authentication Manager....
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(vectorRegisteredUser.getUserName(),vectorRegisteredUser.getPassword()));

            JwtTokenResponse jwtTokenResponse = JwtTokenResponse.builder()
                    .accessToken(JWTUtil.generateAccessToken(vectorRegisteredUser))
                    .refreshToken(JWTUtil.genrateRefreshToken(vectorRegisteredUser))
                    .build();
            return ResponseUtil.createSuccessResponse(HttpStatus.OK,jwtTokenResponse);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred",
                    "ERROR: An error occurred while processing your request.",
                    e.getMessage()
            );
        }
    }

    @PreAuthorize("SUPER_ADMIN")
    @GetMapping("getAllVectorRegisterdUser")
    public ResponseEntity<?> getRegisteredVectorUsers(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        return registrationService.getRegisteredVectorUsers(Integer.parseInt(userId));
    }

    @GetMapping("/getVectorAdminUserByID")
    public ResponseEntity<?> getVectorAdminUserByID(@RequestParam("vectorAdminUserID") Integer vectorAdminUserID){
        return registrationService.getVectorAdminUserByID(vectorAdminUserID);
    }

    @GetMapping("/getVectorUserByID")
    public ResponseEntity<?> getVectorUserByID(@RequestParam("getVectorUserByID") Integer getVectorUserByID){
        return registrationService.getVectorUserByID(getVectorUserByID);
    }
}
