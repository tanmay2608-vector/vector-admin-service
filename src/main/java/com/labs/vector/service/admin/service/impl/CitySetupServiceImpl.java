package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateCityRequest;
import com.labs.vector.service.admin.dto.response.ListOfCityResponse;
import com.labs.vector.service.admin.dto.response.ListOfStateResponse;
import com.labs.vector.service.admin.model.CityMaster;
import com.labs.vector.service.admin.model.StateMaster;
import com.labs.vector.service.admin.repository.CityMasterRepository;
import com.labs.vector.service.admin.service.CitySetupService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class CitySetupServiceImpl implements CitySetupService{

    @Autowired
    CityMasterRepository cityMasterRepository;

    @Override
    public ResponseEntity<?> createUpdateCity(CreateCityRequest createCityRequest) {
       try{
           if(createCityRequest.getCityID() == 0) {
               Optional<CityMaster> cityMaster = cityMasterRepository.findByCityNameAndStateID(createCityRequest.getCityName(), createCityRequest.getStateID());
               if (cityMaster.isPresent()) {
                   ResponseUtil.createErrorResponse(
                           HttpStatus.BAD_REQUEST,
                           "Data duplicates",
                           "ERROR: City Name already exists",
                           "Please choose a different city."
                   );
               }
           }

           //If current city is want to make default then updating existing default city....
           if(StringUtils.equalsAnyIgnoreCase("Y", createCityRequest.getIsDefault())){
               Optional<CityMaster> isDefaultCity = cityMasterRepository.findByIsDefault(createCityRequest.getIsDefault());
               if(isDefaultCity.isPresent()){
                   isDefaultCity.get().setIsDefault("N");
                   cityMasterRepository.save(isDefaultCity.get());
               }
           }

           CityMaster city = new CityMaster();

           //In case of update state....
           if(createCityRequest.getCityID() > 0){
               Optional<CityMaster> existingCity = cityMasterRepository.findById(createCityRequest.getCityID());
               if(existingCity.isPresent()) {
                   city = existingCity.get();
               }
           }

           city.setCityName(createCityRequest.getCityName());
           city.setSortOrder(createCityRequest.getSortOrder());
           city.setIsDefault(createCityRequest.getIsDefault());
           city.setStateID(createCityRequest.getStateID());
           city.setCountryID(createCityRequest.getCountryID());
           city.setPincode(createCityRequest.getPincode());
           city.setStateName(createCityRequest.getStateName());

           cityMasterRepository.save(city);
           ResponseEntity.ok(city);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public ResponseEntity<?> getAllCity() {
        try {
            List<CityMaster> cityMasterList = cityMasterRepository.findAll();
            if(cityMasterList != null && !cityMasterList.isEmpty()){
                ListOfCityResponse listOfCityResponse = new ListOfCityResponse();
                listOfCityResponse.setCityMasters(cityMasterList);
                return ResponseEntity.ok(listOfCityResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCity(Integer cityID) {
        try {
            Optional<CityMaster> cityMaster = cityMasterRepository.findById(cityID);
            if(cityMaster.isPresent()){
                cityMasterRepository.delete(cityMaster.get());
                ResponseEntity.ok("City has been deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCityByStateID(Integer stateID) {
        try {
            List<CityMaster> cityMasterList  = cityMasterRepository.findByStateID(stateID);
            if(cityMasterList != null && cityMasterList.size() > 0){
                ListOfCityResponse listOfCityResponse = new ListOfCityResponse();
                listOfCityResponse.setCityMasters(cityMasterList);
                return ResponseEntity.ok(listOfCityResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
