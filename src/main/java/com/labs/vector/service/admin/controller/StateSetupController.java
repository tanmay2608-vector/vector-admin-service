package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateStateRequest;
import com.labs.vector.service.admin.service.StateSetupService;
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

@RestController
@Validated
@RequestMapping("api/admin/vector-service/v1/state-setup")
@Tag(name = "State Setup", description = "State setop api")
public class StateSetupController {
    private static final Logger log = LoggerFactory.getLogger(StateSetupController.class);

    @Autowired
    StateSetupService stateSetupService;

    /*------------------------------------------   Create And Update State  --------------------------------------------*/
    @PostMapping("/createUpdateState")
    public ResponseEntity<?> createUpdateState(@Valid @RequestBody CreateStateRequest createStateRequest, BindingResult result){
        log.info("Create or update state request:{}",createStateRequest);
        if(result.hasErrors()){
            log.info("Please correct the input fields as per the validation rules.");
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return stateSetupService.createUpdateState(createStateRequest);
    }

    /*------------------------------------------   Load states based on country  --------------------------------------------*/
    @GetMapping("/getStates/{countryID}")
    public ResponseEntity<?> getStatesByCountryID(@PathVariable Integer countryID){
        log.info("Fetch state with Id:{}",countryID);
        return stateSetupService.getAllStatesByCountryID(countryID);
    }

    /*------------------------------------------   Load Cities and Country based on state  --------------------------------------------*/
    @GetMapping("/{stateId}/cities-districts")
    public ResponseEntity<?> getCitiesAndDistrictByStateID(@PathVariable Integer stateID){
        log.info("Fetch cities and districts with Id:{}",stateID);
        return stateSetupService.getCitiesAndDistrictByStateID(stateID);
    }

    /*------------------------------------------ Delete state --------------------------------------------*/
    @DeleteMapping("/deleteState/{stateID}")
    public ResponseEntity<?> deleteStates(@PathVariable Integer stateID){
        log.info("Request to delete state with Id :{}",stateID);
        return stateSetupService.deleteState(stateID);
    }

}
