package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateVillageRequest;
import com.labs.vector.service.admin.dto.response.ListOfVillageResponse;
import com.labs.vector.service.admin.model.VillageMaster;
import com.labs.vector.service.admin.repository.VillageMasterRepository;
import com.labs.vector.service.admin.service.VillageSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VillageSetupServiceImpl implements VillageSetupService {
    private static final Logger log = LoggerFactory.getLogger(VillageSetupServiceImpl.class);

    @Autowired
    VillageMasterRepository  villageMasterRepository;

    @Override
    public ResponseEntity<?> createUpdateVillage(CreateVillageRequest createVillageRequest) {
        try{
            //checking villageMaster already exits for respective city...
            log.info("Request to create or update village received:{}",createVillageRequest);
            if(createVillageRequest.getVillageID() == 0) {
                Optional<VillageMaster> villageMaster = villageMasterRepository.findByTalukaIDAndVillageName(createVillageRequest.getTalukaID(),createVillageRequest.getVillageName());
               log.info("Village : {}",villageMaster);
                if (villageMaster.isPresent()) {
                    log.info(" Village Name already exists, Village Name already exists.");
                    ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Village Name already exists",
                            "Please choose a different village."
                    );
                }
            }

            //If current villageMaster is want to make default then updating existing default villageMaster....
            if(StringUtils.equalsAnyIgnoreCase("Y", createVillageRequest.getIsDefault())){
                Optional<VillageMaster> isDefaultVillage = villageMasterRepository.findByIsDefault(createVillageRequest.getIsDefault());
                log.info("Default Village :{}",isDefaultVillage);
                if(isDefaultVillage.isPresent()){
                    isDefaultVillage.get().setIsDefault("N");
                    villageMasterRepository.save(isDefaultVillage.get());
                }
            }

            VillageMaster villageMaster = new VillageMaster();

            //In case of update villageMaster....
            if(createVillageRequest.getVillageID() > 0){
                Optional<VillageMaster> existingVillage = villageMasterRepository.findById(createVillageRequest.getVillageID());
               log.info("Existing Village :{}",existingVillage);
                if(existingVillage.isPresent()) {
                    villageMaster = existingVillage.get();
                }
            }

            villageMaster.setVillageName(createVillageRequest.getVillageName());
            villageMaster.setSortOrder(createVillageRequest.getSortOrder());
            villageMaster.setIsDefault(createVillageRequest.getIsDefault());
            villageMaster.setTalukaID(createVillageRequest.getTalukaID());
            villageMaster.setTalukaName(createVillageRequest.getTalukaName());

            villageMasterRepository.save(villageMaster);
            log.info("Village saved successfully:{}",villageMaster);
            return ResponseEntity.ok(villageMaster);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to ceate update village", e);
        }
    }

    @Override
    public ResponseEntity<?> deleteVillage(Integer villagID) {
        try {
            Optional<VillageMaster> regionMaster = villageMasterRepository.findById(villagID);
            log.info("Region :{}", regionMaster);
             if(regionMaster.isPresent()){
                villageMasterRepository.deleteById(villagID);
                log.info("Region has been deleted successfully!");
               return ResponseEntity.ok("Region has been deleted successfully!");
            }else {
                 log.info("Village not found");
                return ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT, "village not found", "Invalid villag ID: " + villagID, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete village", e);
        }
    }

    @Override
    public ResponseEntity<?> getAllVillageByTalukaID(Integer talukaID) {
        try {
            if (talukaID > 0) {
                Optional<List<VillageMaster>> villageMasterListOptional = villageMasterRepository.findByTalukaID(talukaID);
                if (villageMasterListOptional.isPresent()) {
                    ListOfVillageResponse listOfVillageResponse = new ListOfVillageResponse();
                    listOfVillageResponse.setVillageMasters(villageMasterListOptional.get());
                    log.info("Village :{}",villageMasterListOptional);
                    return ResponseEntity.ok(listOfVillageResponse);
                }
            }else {
                log.info("Village not found");
                return ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT, "village not found", "Invalid taluka ID: " + talukaID, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all Village By TalukaID", e);
        }
        return null;
    }

    @Override
    public String deleteAllVillageByTalukaID(Integer talukaID) {
        try{
            Optional<List<VillageMaster>> villageMastersOptional = villageMasterRepository.findByTalukaID(talukaID);
            log.info("Village :{}", villageMastersOptional);
            if(villageMastersOptional.isPresent()){
                villageMasterRepository.deleteAll(villageMastersOptional.get());
                log.info("Village deleted successfully");
                return "SUCCESS";
            } else {
                return "ERROR: Invalid taluka Id: "+talukaID;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete all Village By TalukaID", e);
        }
    }
}
