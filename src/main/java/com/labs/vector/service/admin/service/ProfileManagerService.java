package com.labs.vector.service.admin.service;

import org.springframework.http.ResponseEntity;

public interface ProfileManagerService {
    ResponseEntity<?> getProfileDetails(Integer vectorUserID);
}
