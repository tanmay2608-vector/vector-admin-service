package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateTalukaRequest;
import com.labs.vector.service.admin.model.DistrictMaster;
import com.labs.vector.service.admin.model.TalukaMaster;
import com.labs.vector.service.admin.repository.TalukaMasterRepository;
import com.labs.vector.service.admin.service.TalukaSetupService;
import com.labs.vector.service.admin.service.VillageSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalukaSetupServiceImpl implements TalukaSetupService {

    @Autowired
    TalukaMasterRepository talukaMasterRepository;

    @Autowired
    VillageSetupService villageSetupService;

    @Override
    public ResponseEntity<?> createUpdateTaluka(CreateTalukaRequest createTalukaRequest) {
        try{
            if(createTalukaRequest.getTalukaID() == 0){
                Optional<TalukaMaster> talukaMaster = talukaMasterRepository.findByDistrictIDAndTalukaName(createTalukaRequest.getDistrictID(), createTalukaRequest.getTalukaName());
                if(talukaMaster.isPresent()){
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: District Name already exists",
                            "Please choose a different country."
                    );
                }
            }

            if(StringUtils.equalsAnyIgnoreCase(createTalukaRequest.getIsDefault(),"Y")){
                Optional<TalukaMaster> defaultTaluka = talukaMasterRepository.findByIsDefault(createTalukaRequest.getIsDefault());
                if(defaultTaluka.isPresent()){
                    defaultTaluka.get().setIsDefault("N");
                    talukaMasterRepository.save(defaultTaluka.get());
                }
            }

            TalukaMaster talukaMaster = new TalukaMaster();

            if(createTalukaRequest.getTalukaID() != null && createTalukaRequest.getTalukaID() > 0){
                Optional<TalukaMaster> existingTaluka = talukaMasterRepository.findById(createTalukaRequest.getTalukaID());
                if(existingTaluka.isPresent()){
                    talukaMaster = existingTaluka.get();
                }
            }
            talukaMaster.setTalukaName(createTalukaRequest.getTalukaName());
            talukaMaster.setDistrictID(createTalukaRequest.getDistrictID());
            talukaMaster.setDistrictName(createTalukaRequest.getDistrictName());
            talukaMaster.setSortOrder(createTalukaRequest.getSortOrder());
            talukaMaster.setIsDefault(createTalukaRequest.getIsDefault());
            talukaMasterRepository.save(talukaMaster);
            return ResponseEntity.ok(talukaMaster);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteTaluka(Integer talukaID) {
        try{
            Optional<TalukaMaster> talukaMaster = talukaMasterRepository.findById(talukaID);
            if(talukaMaster.isPresent()){
                villageSetupService.deleteAllVillageByTalukaID(talukaID);
                talukaMasterRepository.deleteById(talukaID);
                ResponseEntity.ok("Taluka has been deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteTalukaByDistrictID(Integer districtID) {
        try {
            Optional<List<TalukaMaster>> talukaMastersOptional = talukaMasterRepository.findByDistrictID(districtID);
            if (talukaMastersOptional.isPresent()) {
                talukaMastersOptional.get().forEach(talukaMaster -> deleteAllTalukaForDistrict(talukaMaster.getTalukaID()));
                return "SUCCESS";
            } else {
                return "NO_TALUKA_FOUND";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void deleteAllTalukaForDistrict(Integer talukaID){
        try {
            if(talukaID > 0){
                //Deleting taluka for respective districts...
                villageSetupService.deleteAllVillageByTalukaID(talukaID);
                talukaMasterRepository.deleteById(talukaID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
