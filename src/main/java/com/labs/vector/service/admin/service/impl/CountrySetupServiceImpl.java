package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateCountryRequest;
import com.labs.vector.service.admin.dto.response.ListOfCountryResponse;
import com.labs.vector.service.admin.model.CountryMaster;
import com.labs.vector.service.admin.repository.CountryMasterRepository;
import com.labs.vector.service.admin.service.CountrySetupService;
import com.labs.vector.service.admin.service.StateSetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountrySetupServiceImpl implements CountrySetupService {

    @Autowired
    CountryMasterRepository countryMasterRepository;

    @Autowired
    StateSetupService stateSetupService;

    @Override
    public ResponseEntity<?> createUpdateCountry(CreateCountryRequest createCountryRequest) {
        try {
            if(createCountryRequest.getCountryId() == 0) {
                Optional<CountryMaster> country = countryMasterRepository.findByCountryName(createCountryRequest.getCountry());
                if (country.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Country Name already exists",
                            "Please choose a different country."
                    );
                }
            }

            if(StringUtils.endsWithIgnoreCase("Y",createCountryRequest.getIsDefault())) {
                Optional<CountryMaster> defaultContry = countryMasterRepository.findByIsDefault(createCountryRequest.getIsDefault());
                if(defaultContry.isPresent()){
                    defaultContry.get().setIsDefault("N");
                    countryMasterRepository.save(defaultContry.get());
                }
            }

            CountryMaster countryMaster = new CountryMaster();

            if(createCountryRequest.getCountryId() != null && createCountryRequest.getCountryId() > 0){
                Optional<CountryMaster> existingCountry = countryMasterRepository.findById(createCountryRequest.getCountryId());
                if(existingCountry.isPresent()){
                    countryMaster = existingCountry.get();
                }
            }

            countryMaster.setCountryName(createCountryRequest.getCountry());
            countryMaster.setCountryCode(createCountryRequest.getCountryCode());
            countryMaster.setPhoneLength(createCountryRequest.getPhoneLength());
            countryMaster.setSortOrder(createCountryRequest.getSortOrder());
            countryMaster.setAdditionalInfo(createCountryRequest.getAdditionalInfo());
            countryMaster.setPincodeLength(createCountryRequest.getPincodeLength());
            countryMaster.setIsDefault(createCountryRequest.getIsDefault());
            countryMasterRepository.save(countryMaster);

            return ResponseEntity.ok(countryMaster);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCountry() {
        try {
            List<CountryMaster> countryMasterList = countryMasterRepository.findAll();
            if(countryMasterList != null && !countryMasterList.isEmpty()){
                ListOfCountryResponse listOfCountryResponse = new ListOfCountryResponse();
                listOfCountryResponse.setCountryMasters(countryMasterList);
                return ResponseEntity.ok(listOfCountryResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCountry(Integer countryID) {
        try {
            Optional<CountryMaster> existingCountry = countryMasterRepository.findById(countryID);
            if(existingCountry.isPresent() && existingCountry.get() != null){
                //Deleting states for respective country..
                stateSetupService.deleteStatesByCountryID(countryID);

                countryMasterRepository.deleteById(countryID);
                return ResponseEntity.ok("Country has been deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
