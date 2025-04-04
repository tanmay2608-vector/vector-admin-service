package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateRegionRequest;
import com.labs.vector.service.admin.service.RegionSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/vector-service/v1/region-setup")
@Tag(name = "Region Setup", description = "Region Setup Controller")
public class RegionSetupController {

    @Autowired
    RegionSetupService regionSetupService;

    /*------------------------------------------   Create And Update Region  --------------------------------------------*/
    @PostMapping("/createUpdateRegion")
    public ResponseEntity<?> createUpdateRegion(@Valid @RequestBody CreateRegionRequest createRegionRequest, BindingResult result){
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return regionSetupService.createUpdateRegion(createRegionRequest);
    }

    /*------------------------------------------   Load Region By City ID  --------------------------------------------*/
    @GetMapping("/getRegions/{cityID}")
    public ResponseEntity<?> getRegionsByCityID(@PathVariable Integer cityID){
        return regionSetupService.getAllRegionByCityID(cityID);
    }

    /*------------------------------------------  Delete Region  --------------------------------------------*/
    @DeleteMapping("/deleteRegion/{regionID}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer regionID){
        return regionSetupService.deleteRegion(regionID);
    }

}
