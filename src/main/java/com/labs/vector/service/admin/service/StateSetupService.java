package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateStateRequest;
import org.springframework.http.ResponseEntity;

public interface StateSetupService {

    ResponseEntity<?> createUpdateState(CreateStateRequest createStateRequest);
    ResponseEntity<?> getAllStates();
    ResponseEntity<?> deleteState(Integer stateID);
    ResponseEntity<?> getAllStatesByCountryID(Integer countryID);
    ResponseEntity<?> getCitiesAndDistrictByStateID(Integer stateID);
    String deleteStatesByCountryID(Integer countryID);
}
