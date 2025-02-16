package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateVillageRequest;
import org.springframework.http.ResponseEntity;

public interface VillageSetupService {

    ResponseEntity<?> createUpdateVillage(CreateVillageRequest createVillageRequest);
    ResponseEntity<?> deleteVillage(Integer villagID);
    ResponseEntity<?> getAllVillageByTalukaID(Integer talukaID);
    String deleteAllVillageByTalukaID(Integer talukaID);
}
