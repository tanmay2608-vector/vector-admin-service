package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateTalukaRequest;
import org.springframework.http.ResponseEntity;

public interface TalukaSetupService {
    ResponseEntity<?> createUpdateTaluka(CreateTalukaRequest createTalukaRequest);
    ResponseEntity<?> deleteTaluka(Integer talukaID);
    String deleteTalukaByDistrictID(Integer districtID);
}
