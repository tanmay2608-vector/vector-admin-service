package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.response.JwtTokenResponse;
import com.labs.vector.service.admin.service.impl.UserDetailsServiceImp;
import com.labs.vector.service.admin.utils.JWTUtil;
import com.labs.vector.service.admin.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/generateAccessToken")
    public ResponseEntity<?> generateAccessToken(HttpServletRequest request){
        log.info("Recevied request to generate access token");
        String refreshToken = request.getHeader("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            log.error("Missing refreshToken in request headers.");
            throw new IllegalArgumentException("Missing refreshToken in the request headers");
        }
        log.info("Extracting username from refresh token.");
        String userName = JWTUtil.getUserNameFromRefreshToken(refreshToken);

        log.info("Loading user details for username:{}",userName);
        UserDetails vectorRegisteredUser = userDetailsServiceImp.loadUserByUsername(userName);

        log.info("Generating access token for user:{}",userName);
        String accessToken = JWTUtil.generateAccessToken(vectorRegisteredUser);

        JwtTokenResponse jwtTokenResponse = JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        log.info("Access token generated successfully for user:{}",userName);
        return ResponseUtil.createSuccessResponse(HttpStatus.OK,jwtTokenResponse);
    }
}
