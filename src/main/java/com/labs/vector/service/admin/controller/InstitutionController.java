package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateSchoolRequest;
import com.labs.vector.service.admin.dto.request.CreateUniversityRequest;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(InstitutionController.class);
    /*------------------------------ School APIs -------------------------------------------*/
    @PostMapping("/createUpdateSchool")
    public ResponseEntity<?> createUpdateSchool(@Valid @RequestBody CreateSchoolRequest request, BindingResult result) {
        log.info("Create or update school request:{}", request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validations rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllSchools")
    public ResponseEntity<?> getAllSchools() {
        log.info("Fectching all schools.");
        return null;
    }

    @DeleteMapping("/deleteSchool/{schoolId}")
    public ResponseEntity<?> deleteSchool(@PathVariable Integer schoolId) {
        log.info("Request to delete school with Id :{}",schoolId);
        return null;
    }

    /*------------------------------ University APIs -------------------------------------------*/
    @PostMapping("/createUpdateUniversity")
    public ResponseEntity<?> createUpdateUniversity(@Valid @RequestBody CreateUniversityRequest request, BindingResult result) {
        log.info("Create or Update University request :{}", request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validations rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllUniversities")
    public ResponseEntity<?> getAllUniversities() {
        log.info("Fectching all universities.");
        return null;
    }

    @DeleteMapping("/deleteUniversity/{universityId}")
    public ResponseEntity<?> deleteUniversity(@PathVariable Integer universityId) {
        log.info("Request to delete university with Id :{}",universityId);
        return null;
    }
}
