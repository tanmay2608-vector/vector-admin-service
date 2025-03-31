package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateDistrictRequest;
import com.labs.vector.service.admin.model.CountryMaster;
import com.labs.vector.service.admin.model.DistrictMaster;
import com.labs.vector.service.admin.repository.DistrictMasterRepository;
import com.labs.vector.service.admin.service.DistrictSetupService;
import com.labs.vector.service.admin.service.TalukaSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DistrictSetupServiceImpl implements DistrictSetupService {

    @Autowired
    DistrictMasterRepository districtMasterRepository;

    @Autowired
    TalukaSetupService talukaSetupService;

    @Override
    public ResponseEntity<?> createUpdateDistrict(CreateDistrictRequest createDistrictRequest) {
        try{
            if(createDistrictRequest.getDistrictID() == 0){
                Optional<DistrictMaster> districtMaster = districtMasterRepository.findByStateIDAndDistrictName(createDistrictRequest.getStateID(), createDistrictRequest.getDistrictName());
                if(districtMaster.isPresent()){
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: District Name already exists",
                            "Please choose a different country."
                    );
                }
            }

            if(StringUtils.equalsAnyIgnoreCase(createDistrictRequest.getIsDefault(),"Y")){
                Optional<DistrictMaster> defaultDistrict = districtMasterRepository.findByIsDefault(createDistrictRequest.getIsDefault());
                if(defaultDistrict.isPresent()){
                    defaultDistrict.get().setIsDefault("N");
                    districtMasterRepository.save(defaultDistrict.get());
                }
            }

            DistrictMaster districtMaster = new DistrictMaster();

            if(createDistrictRequest.getStateID() != null && createDistrictRequest.getStateID() > 0){
                Optional<DistrictMaster> existingDistrict = districtMasterRepository.findById(createDistrictRequest.getDistrictID());
                if(existingDistrict.isPresent()){
                    districtMaster = existingDistrict.get();
                }
            }
            districtMaster.setDistrictName(createDistrictRequest.getDistrictName());
            districtMaster.setStateID(createDistrictRequest.getStateID());
            districtMaster.setStateName(createDistrictRequest.getStateName());
            districtMaster.setSortOrder(createDistrictRequest.getSortOrder());
            districtMaster.setIsDefault(createDistrictRequest.getIsDefault());
            districtMasterRepository.save(districtMaster);

            return ResponseEntity.ok(districtMaster);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create & Update District",e);
        }
    }

    @Override
    public ResponseEntity<?> deleteDistrict(Integer districtID) {
        try{
           Optional<DistrictMaster> districtMaster = districtMasterRepository.findById(districtID);
           if(districtMaster.isPresent()){
               //Deleting taluka for district..
               talukaSetupService.deleteTalukaByDistrictID(districtID);
               districtMasterRepository.deleteById(districtID);
               ResponseEntity.ok("District has been deleted successfully!");
           }else {
               return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"District not found", "Invalid District Id : "+districtID,"");
           }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete District",e);
        }
        return null;
    }

    @Override
    public List<DistrictMaster> getAllDistrictByStateID(Integer stateID) {
        Optional<List<DistrictMaster>> districtMasterList = districtMasterRepository.findByStateID(stateID);
        return districtMasterList.get();
    }

    @Override
    public String deleteDistrictByStateID(Integer stateID) {
        try{
            Optional<List<DistrictMaster>> districtMasterOptional = districtMasterRepository.findByStateID(stateID);
            if (districtMasterOptional.isPresent()) {
                districtMasterOptional.get().forEach(districtMaster -> deleteAllDistrictForState(districtMaster.getDistrictID()));
                return "SUCCESS";
            } else {
                return "NO_DISTRICTS_FOUND";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete District By StateID",e);
        }
    }

    private void deleteAllDistrictForState(Integer districtID){
        try {
            if(districtID > 0){
                //Deleting taluka for respective district...
                talukaSetupService.deleteTalukaByDistrictID(districtID);
                districtMasterRepository.deleteById(districtID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete All District For State",e);
        }
    }
}
