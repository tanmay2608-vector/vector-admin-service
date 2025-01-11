package com.labs.vector.service.admin.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JWTUtil {
    private static final String SECRET_KEY = "Q8vLdGx3Z5KrjT6NmWpX2ByHaC9ETWpXnC7KhJ0VR8Mw5OqZ";
    public static String generateAccessToken(Object data){
        return Jwts.builder()
                .claim("data",data)
                .setIssuedAt(new Date(Instant.now().toEpochMilli()))
                .setExpiration(new Date(Instant.now().plus(5, ChronoUnit.MINUTES).toEpochMilli()))
                .signWith(secretKey())
                .compact();
    }

    public static SecretKey secretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
