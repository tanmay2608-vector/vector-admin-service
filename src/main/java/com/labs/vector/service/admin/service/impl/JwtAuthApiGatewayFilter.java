package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthApiGatewayFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");

            if(StringUtils.isBlank(authHeader)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                String jsonResponse = """
                   {
                        "error" : "Unauthorized",
                        "statusCode" : "401",
                        "message" : "Access token is invalid or missing",
                        "description" : "Please provide valid access token" 
                   }
                    """;

                response.getWriter().write(jsonResponse);
                response.getWriter().flush();
                return;
            }

            String token = authHeader.substring("Bearer ".length());

            String userName = JWTUtil.getUserNameByToken(token);
            //We are validating user based on role also o we are retriving userdetails from db by using userName
            UserDetails vectoruser = userDetailsServiceImp.loadUserByUsername(userName);

            //Validate Token by using SecurityContextHolder....
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userName,null,vectoruser.getAuthorities()));

            //If toke is valid ...........
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();
            e.printStackTrace();
            return;
        }
    }
}
