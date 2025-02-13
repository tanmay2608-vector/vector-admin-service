package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateCityRequest;
import org.springframework.http.ResponseEntity;

public interface CitySetupService {
    ResponseEntity<?> createUpdateCity(CreateCityRequest createCityRequest);
    ResponseEntity<?> getAllCity();
    ResponseEntity<?> deleteCity(Integer cityID);
    ResponseEntity<?> getAllCityByStateID(Integer stateID);
}
