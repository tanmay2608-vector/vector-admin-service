package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateTalukaRequest;
import com.labs.vector.service.admin.service.TalukaSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@Tag(name = "Taluka Setup", description = "Taluka Setup Api")
public class TalukaSetupController {
    private static final Logger log = LoggerFactory.getLogger(TalukaSetupController.class);

    @Autowired
    TalukaSetupService talukaSetupService;

    @PostMapping("/createUpdateTaluka")
    public ResponseEntity<?> createUpdateTaluka(@Valid CreateTalukaRequest createTalukaRequest, BindingResult result){
        log.info("Create or Update taulka request:{}",createTalukaRequest);
        if(result.hasErrors()){
            log.info("Please correct the input fields as per the validation rules.");
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validatrion Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return talukaSetupService.createUpdateTaluka(createTalukaRequest);
    }

    @DeleteMapping("/deleteTaluka/{districtID}")
    public ResponseEntity<?> deleteTaluka(Integer districtID){
        log.info("Request to delete taluka with Id :{}", districtID);

        return talukaSetupService.deleteTaluka(districtID);
    }
}
