package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.model.VectorRegisteredUser;
import com.labs.vector.service.admin.repository.VectorRegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private VectorRegisteredUserRepository vectorRegisteredUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<VectorRegisteredUser> vectorUser = vectorRegisteredUserRepository.findByUserName(username);
        if(vectorUser.isPresent()){
            VectorRegisteredUser vectorRegisteredUser = vectorUser.get();
            return User.builder()
                    .username(vectorRegisteredUser.getUserName())
                    .password(vectorRegisteredUser.getPassword())
                    // .roles(vectorRegisteredUser.getRoles().toArray(String[]::new))
                    .build();
        }else {
            throw new UsernameNotFoundException("User does exist");
        }
    }
}
