package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateStateRequest;
import com.labs.vector.service.admin.service.StateSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    @Autowired
    StateSetupService stateSetupService;

    @PostMapping("/createUpdateState")
    public ResponseEntity<?> createUpdateState(@Valid @RequestBody CreateStateRequest createStateRequest, BindingResult result){
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return stateSetupService.createUpdateState(createStateRequest);
    }

    @GetMapping("/getAllStates")
    public ResponseEntity<?> getAllStates(){
        return stateSetupService.getAllStates();
    }

    @GetMapping("/getStates/{countryID}")
    public ResponseEntity<?> getStatesByCountryID(@PathVariable Integer countryID){
        return stateSetupService.getAllStatesByCountryID(countryID);
    }

    @DeleteMapping("/deleteState/{stateID}")
    public ResponseEntity<?> deleteStates(@PathVariable Integer stateID){
        return stateSetupService.deleteState(stateID);
    }

}
