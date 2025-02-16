package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateCountryRequest;
import com.labs.vector.service.admin.service.CountrySetupService;
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
@RequestMapping("api/admin/vector-service/v1/country-setup")
@Tag(name = "Country Setup", description = "Contry Setup Api.")
public class CountrySetupController {

    @Autowired
    CountrySetupService countrySetupService;

    /*------------------------------------------   Create And Update Country  --------------------------------------------*/
    @PostMapping("createUpdateContry")
    public ResponseEntity<?> createUpdateContry(@Valid @RequestBody CreateCountryRequest createCountryRequest, BindingResult result){
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return countrySetupService.createUpdateCountry(createCountryRequest);
    }

    /*------------------------------------------   Load Country  --------------------------------------------*/
    @GetMapping("getAllCountries")
    public ResponseEntity<?> getAllCountries(){
        return countrySetupService.getAllCountry();
    }

    /*------------------------------------------ Delete Country --------------------------------------------*/
    @DeleteMapping("deleteCountry/{countryID}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryID){
        return countrySetupService.deleteCountry(countryID);
    }
}
