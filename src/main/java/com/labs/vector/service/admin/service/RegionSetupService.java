package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateRegionRequest;
import org.springframework.http.ResponseEntity;

public interface RegionSetupService {
    ResponseEntity<?> createUpdateRegion(CreateRegionRequest createRegionRequest);
    ResponseEntity<?> getAllRegion();
    ResponseEntity<?> deleteRegion(Integer regionID);
    ResponseEntity<?> getAllRegionByCityID(Integer cityID);

}
