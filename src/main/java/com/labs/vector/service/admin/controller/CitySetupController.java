package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateCityRequest;
import com.labs.vector.service.admin.service.CitySetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/admin/vector-service/v1/city-setup")
@Tag(name = "City Setup", description = "City Setup Api")
public class CitySetupController {

    @Autowired
    CitySetupService citySetupService;

    /*------------------------------------------   Create And Update City  --------------------------------------------*/
    @PostMapping("/createUpdateCity")
    public ResponseEntity<?> createUpdateCity(@Valid @RequestBody CreateCityRequest createCityRequest, BindingResult result){
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return citySetupService.createUpdateCity(createCityRequest);
    }

    /*------------------------------------------   Delete Cities  --------------------------------------------*/

    @DeleteMapping("/deleteCity/{cityID}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer cityID){
        return citySetupService.deleteCity(cityID);
    }
}
