package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateVillageRequest;
import com.labs.vector.service.admin.service.VillageSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
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
@Tag(name = "Village Setup", description = "Village Setup Api")
public class VillageSetupController {

    @Autowired
    VillageSetupService villageSetupService;

    @PostMapping("/createUpdateVillage")
    public ResponseEntity<?> createUpdateVillage(@Valid CreateVillageRequest createVillageRequest, BindingResult result){
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validatrion Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return villageSetupService.createUpdateVillage(createVillageRequest);
    }

    @DeleteMapping("/deleteVillage/{villageID}")
    public ResponseEntity<?> deleteVillage(Integer villageID){
        return villageSetupService.deleteVillage(villageID);
    }

    @GetMapping("/getVillages/{talukaID}")
    public ResponseEntity<?> getVillagesByTalukaID(@PathVariable Integer talukaID){
        return villageSetupService.getAllVillageByTalukaID(talukaID);
    }
}
