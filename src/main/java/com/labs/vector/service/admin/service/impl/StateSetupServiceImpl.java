package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateStateRequest;
import com.labs.vector.service.admin.dto.response.ListOfCityDistrictResponse;
import com.labs.vector.service.admin.dto.response.ListOfStateResponse;
import com.labs.vector.service.admin.model.CityMaster;
import com.labs.vector.service.admin.model.DistrictMaster;
import com.labs.vector.service.admin.model.StateMaster;
import com.labs.vector.service.admin.repository.StateMasterRepository;
import com.labs.vector.service.admin.service.CitySetupService;
import com.labs.vector.service.admin.service.DistrictSetupService;
import com.labs.vector.service.admin.service.StateSetupService;
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
public class StateSetupServiceImpl implements StateSetupService {
    private static final Logger log = LoggerFactory.getLogger(StateSetupServiceImpl.class);

    @Autowired
    StateMasterRepository stateMasterRepository;

    @Autowired
    CitySetupService citySetupService;

    @Autowired
    DistrictSetupService districtSetupService;

    @Override
    public ResponseEntity<?> createUpdateState(CreateStateRequest createStateRequest) {
        try{
            //checking state already exits for respective country...
            if(createStateRequest.getStateID() == 0) {
                Optional<StateMaster> stateMaster = stateMasterRepository.findByStateNameAndCountryID(createStateRequest.getStateName(), createStateRequest.getCountryID());
                log.info("State :{}",stateMaster.toString());
                if (stateMaster.isPresent()) {
                    log.info("State name already exists");
                    ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: State Name already exists",
                            "Please choose a different state."
                    );
                }
            }

            //If current state is want to make default then updating existing default state....
            if(StringUtils.equalsAnyIgnoreCase("Y", createStateRequest.getIsDefault())){
                Optional<StateMaster> isDefaultState = stateMasterRepository.findByIsDefault(createStateRequest.getIsDefault());
                if(isDefaultState.isPresent()){
                    isDefaultState.get().setIsDefault("N");
                    stateMasterRepository.save(isDefaultState.get());
                }
            }

            StateMaster state = new StateMaster();

            //In case of update state....
            if(createStateRequest.getStateID() > 0){
                Optional<StateMaster> existingState = stateMasterRepository.findById(createStateRequest.getStateID());
                if(existingState.isPresent()) {
                    state = existingState.get();
                }
            }

            state.setStateName(createStateRequest.getStateName());
            state.setSortOrder(createStateRequest.getSortOrder());
            state.setIsDefault(createStateRequest.getIsDefault());
            state.setCountryID(createStateRequest.getCountryID());
            state.setShortStateName(createStateRequest.getShortStateName());

            stateMasterRepository.save(state);
            log.info("State Saved :{}",state.toString());
           return ResponseEntity.ok(state);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create update state ",e);
        }
    }

    @Override
    public ResponseEntity<?> getAllStates(){
        try {
            List<StateMaster> stateMasterList = stateMasterRepository.findAll();
            if(stateMasterList != null && !stateMasterList.isEmpty()){
                ListOfStateResponse listOfStateResponse = new ListOfStateResponse();
                listOfStateResponse.setStateMasters(stateMasterList);
                log.info("All State :{}",stateMasterList.toString());
                return ResponseEntity.ok(listOfStateResponse);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"states not found", "No state exist: ","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all states ",e);
        }
    }

    @Override
    public ResponseEntity<?> getAllStatesByCountryID(Integer countryID){
        try {
            List<StateMaster> stateMasterList = stateMasterRepository.findByCountryID(countryID);
            if(stateMasterList != null && stateMasterList.size() > 0){
                ListOfStateResponse listOfStateResponse = new ListOfStateResponse();
                listOfStateResponse.setStateMasters(stateMasterList);
                log.info("State List by Country :{}",stateMasterList.toString());
                return ResponseEntity.ok(listOfStateResponse);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"ststes not found", "Invalid country ID: "+countryID,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all states by country id ",e);
        }
    }

    @Override
    public ResponseEntity<?> getCitiesAndDistrictByStateID(Integer stateID) {
        try {
            if(stateID != null && stateID > 0) {
                List<CityMaster> cityMasterList = citySetupService.getAllCityByStateID(stateID);
                List<DistrictMaster> districtMasterList = districtSetupService.getAllDistrictByStateID(stateID);

                ListOfCityDistrictResponse listOfCityDistrictResponse = new ListOfCityDistrictResponse();
                listOfCityDistrictResponse.setCityMasters(cityMasterList);
                listOfCityDistrictResponse.setDistrictMasterList(districtMasterList);
                log.info("City List by state:{}",cityMasterList.toString());
                log.info("District List by state:{}",districtMasterList.toString());
                return ResponseEntity.ok(listOfCityDistrictResponse);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"cities and district not found", "Invalid state ID: "+stateID,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get Cities And District By StateID",e);
        }
    }

    @Override
    public String deleteStatesByCountryID(Integer countryID) {
        try {
            List<StateMaster> stateMasterList = stateMasterRepository.findByCountryID(countryID);
            log.info("State : {}", stateMasterList.toString());
            if(stateMasterList != null && stateMasterList.size() > 0){
                for(StateMaster state : stateMasterList){
                    deleteStateById(state.getStateID());
                }
                log.info("State Deleted Successfully.....");
                return "SUCCESS";
            }else {
                return "ERROR: Invalid country Id: "+countryID;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete States By CountryID",e);
        }
    }

    private void deleteStateById(Integer stateID){
        try {
            if(stateID > 0){
                //Deleting cities for respective state..
                citySetupService.deleteCityByStateID(stateID);
                stateMasterRepository.deleteById(stateID);
                log.info("State Successfully Deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete States By ID",e);
        }
    }

    @Override
    public ResponseEntity<?> deleteState(Integer stateID) {
        try {
            Optional<StateMaster> stateMaster = stateMasterRepository.findById(stateID);
            if (stateMaster.isPresent()) {
                //Deleting cities for respective state..
                citySetupService.deleteCityByStateID(stateID);
                districtSetupService.deleteDistrictByStateID(stateID);
                stateMasterRepository.deleteById(stateID);
                log.info("State has been deleted successfully");
                return ResponseEntity.ok("State has been deleted successfully!");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT, "state not found", "Invalid state ID: " + stateID, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete State", e);
        }
    }
}
