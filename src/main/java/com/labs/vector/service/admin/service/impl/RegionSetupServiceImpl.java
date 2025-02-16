package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateRegionRequest;
import com.labs.vector.service.admin.dto.response.ListOfRegionResponse;
import com.labs.vector.service.admin.model.RegionMaster;
import com.labs.vector.service.admin.repository.RegionMasterRepository;
import com.labs.vector.service.admin.service.RegionSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionSetupServiceImpl implements RegionSetupService {

    @Autowired
    RegionMasterRepository regionMasterRepository;

    @Override
    public ResponseEntity<?> createUpdateRegion(CreateRegionRequest createRegionRequest) {
       try{
            //checking region already exits for respective city...
            if(createRegionRequest.getRegionID() == 0) {
                Optional<RegionMaster> regionMaster = regionMasterRepository.findByRegionNameAndCityID(createRegionRequest.getRegionName(),createRegionRequest.getCityID());
                if (regionMaster.isPresent()) {
                    ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Region Name already exists",
                            "Please choose a different region."
                    );
                }
            }

            //If current region is want to make default then updating existing default region....
            if(StringUtils.equalsAnyIgnoreCase("Y", createRegionRequest.getIsDefault())){
                Optional<RegionMaster> isDefaultRegiom = regionMasterRepository.findByIsDefault(createRegionRequest.getIsDefault());
                if(isDefaultRegiom.isPresent()){
                    isDefaultRegiom.get().setIsDefault("N");
                    regionMasterRepository.save(isDefaultRegiom.get());
                }
            }

            RegionMaster region = new RegionMaster();

            //In case of update region....
            if(createRegionRequest.getRegionID() > 0){
                Optional<RegionMaster> existingRegion = regionMasterRepository.findById(createRegionRequest.getRegionID());
                if(existingRegion.isPresent()) {
                    region = existingRegion.get();
                }
            }

            region.setRegionName(createRegionRequest.getRegionName());
            region.setSortOrder(createRegionRequest.getSortOrder());
            region.setIsDefault(createRegionRequest.getIsDefault());
            region.setCityID(createRegionRequest.getCityID());
            region.setPinCode(createRegionRequest.getPinCode());

            regionMasterRepository.save(region);

            ResponseEntity.ok(region);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllRegion() {
        try {
            List<RegionMaster> regionMasterList = regionMasterRepository.findAll();
            if(regionMasterList != null && !regionMasterList.isEmpty()){
                ListOfRegionResponse listOfRegionResponse = new ListOfRegionResponse();
                listOfRegionResponse.setRegionMasters(regionMasterList);
                return ResponseEntity.ok(listOfRegionResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteRegion(Integer regionID) {
        try {
            Optional<RegionMaster> regionMaster = regionMasterRepository.findById(regionID);
            if(regionMaster.isPresent()){
                regionMasterRepository.deleteById(regionID);
                ResponseEntity.ok("Region has been deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
            
    @Override
    public ResponseEntity<?> getAllRegionByCityID(Integer cityID) {
        try {
            List<RegionMaster> regionMasterList = regionMasterRepository.findByCityID(cityID);
            if(regionMasterList != null && regionMasterList.size() > 0){
                ListOfRegionResponse listOfRegionResponse = new ListOfRegionResponse();
                listOfRegionResponse.setRegionMasters(regionMasterList);
                return ResponseEntity.ok(listOfRegionResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteAllRegionByCityID(Integer cityID) {
        try{
           List<RegionMaster> regionMasterList = regionMasterRepository.findByCityID(cityID);
           if(regionMasterList != null && regionMasterList.size()>0){
               regionMasterRepository.deleteAll(regionMasterList);
               return "SUCCESS";
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
