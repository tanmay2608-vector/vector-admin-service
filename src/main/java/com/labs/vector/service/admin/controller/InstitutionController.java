package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateSchoolRequest;
import com.labs.vector.service.admin.dto.request.CreateUniversityRequest;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/admin/vector-service/v1/institution-setup")
@Tag(name = "Institution Setup", description = "Institution Controller Api")
public class InstitutionController {
    /*------------------------------ School APIs -------------------------------------------*/
    @PostMapping("/createUpdateSchool")
    public ResponseEntity<?> createUpdateSchool(@Valid @RequestBody CreateSchoolRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllSchools")
    public ResponseEntity<?> getAllSchools() {
        return null;
    }

    @DeleteMapping("/deleteSchool/{schoolId}")
    public ResponseEntity<?> deleteSchool(@PathVariable Integer schoolId) {
        return null;
    }

    /*------------------------------ University APIs -------------------------------------------*/
    @PostMapping("/createUpdateUniversity")
    public ResponseEntity<?> createUpdateUniversity(@Valid @RequestBody CreateUniversityRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllUniversities")
    public ResponseEntity<?> getAllUniversities() {
        return null;
    }

    @DeleteMapping("/deleteUniversity/{universityId}")
    public ResponseEntity<?> deleteUniversity(@PathVariable Integer universityId) {
        return null;
    }
}
