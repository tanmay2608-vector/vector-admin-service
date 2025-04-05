package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateDistrictRequest;
import com.labs.vector.service.admin.service.DistrictSetupService;
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
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@Tag(name = "District Setup", description = "District Setup Api")
public class DistrictSetupController {
    private static final Logger log = LoggerFactory.getLogger(DistrictSetupController.class);

    @Autowired
    DistrictSetupService districtSetupService;

    @PostMapping("/createUpdateDistrict")
    public ResponseEntity<?> createUpdateDistrict(@Valid CreateDistrictRequest createDistrictRequest, BindingResult result){
        log.info("Create or Update district request:{}",createDistrictRequest);
        if(result.hasErrors()){
            log.info("Please correct the input fields as per the validations rules");
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validatrion Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return districtSetupService.createUpdateDistrict(createDistrictRequest);
    }

    @DeleteMapping("/deleteDistrict/{districtID}")
    public ResponseEntity<?> deleteDistrict(Integer districtID){
        log.info("Request to delete District with Id :{}",districtID);
        return districtSetupService.deleteDistrict(districtID);
    }
}
