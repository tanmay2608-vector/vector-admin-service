package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.service.ProfileManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/admin/vector-service/v1/profile-manager")
@Tag(name = "Profile Manager", description = "Profile manager API")
public class ProfileManagerController {

    @Autowired
    ProfileManagerService profileManagerService;

    @GetMapping("/getProfileDetails/{vectorUserID}")
    public ResponseEntity<?> getProfileDetails(@PathVariable Integer vectorUserID){
        return profileManagerService.getProfileDetails(vectorUserID);
    }

}
