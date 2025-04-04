package com.labs.vector.service.admin.service;
import com.labs.vector.service.admin.dto.request.CreateDistrictRequest;
import com.labs.vector.service.admin.model.DistrictMaster;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DistrictSetupService {
    ResponseEntity<?> createUpdateDistrict(CreateDistrictRequest createDistrictRequest);
    ResponseEntity<?> deleteDistrict(Integer districtID);
    List<DistrictMaster> getAllDistrictByStateID(Integer stateID);
    String deleteDistrictByStateID(Integer stateID);

}
