package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.response.JwtTokenResponse;
import com.labs.vector.service.admin.service.impl.UserDetailsServiceImp;
import com.labs.vector.service.admin.utils.JWTUtil;
import com.labs.vector.service.admin.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/admin/vector-service/v1/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/generateAccessToken")
    public ResponseEntity<?> generateAccessToken(HttpServletRequest request){
        String refreshToken = request.getHeader("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new IllegalArgumentException("Missing refreshToken in the request headers");
        }
        String userName = JWTUtil.getUserNameFromRefreshToken(refreshToken);

        UserDetails vectorRegisteredUser = userDetailsServiceImp.loadUserByUsername(userName);
        String accessToken = JWTUtil.generateAccessToken(vectorRegisteredUser);

        JwtTokenResponse jwtTokenResponse = JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseUtil.createSuccessResponse(HttpStatus.OK,jwtTokenResponse);
    }
}
