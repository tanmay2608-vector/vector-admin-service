package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateCityRequest;
import com.labs.vector.service.admin.model.CityMaster;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CitySetupService {
    ResponseEntity<?> createUpdateCity(CreateCityRequest createCityRequest);
    ResponseEntity<?> getAllCity();
    ResponseEntity<?> deleteCity(Integer cityID);
    List<CityMaster> getAllCityByStateID(Integer stateID);
    String deleteCityByStateID(Integer stateID);
}
