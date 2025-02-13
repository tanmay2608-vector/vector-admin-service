package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateStateRequest;
import com.labs.vector.service.admin.dto.response.ListOfStateResponse;
import com.labs.vector.service.admin.model.StateMaster;
import com.labs.vector.service.admin.repository.StateMasterRepository;
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
public class StateSetupServiceImpl implements StateSetupService {

    @Autowired
    StateMasterRepository stateMasterRepository;

    @Override
    public ResponseEntity<?> createUpdateState(CreateStateRequest createStateRequest) {
        try{
            //checking state already exits for respective country...
            if(createStateRequest.getStateID() == 0) {
                Optional<StateMaster> stateMaster = stateMasterRepository.findByStateNameAndCountryID(createStateRequest.getCountryID(), createStateRequest.getStateName());
                if (stateMaster.isPresent()) {
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

            ResponseEntity.ok(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllStates(){
        try {
            List<StateMaster> stateMasterList = stateMasterRepository.findAll();
            if(stateMasterList != null && !stateMasterList.isEmpty()){
                ListOfStateResponse listOfStateResponse = new ListOfStateResponse();
                listOfStateResponse.setStateMasters(stateMasterList);
                return ResponseEntity.ok(listOfStateResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllStatesByCountryID(Integer countryID){
        try {
            List<StateMaster> stateMasterList = stateMasterRepository.findByCountryID(countryID);
            if(stateMasterList != null && stateMasterList.size() > 0){
                ListOfStateResponse listOfStateResponse = new ListOfStateResponse();
                listOfStateResponse.setStateMasters(stateMasterList);
                return ResponseEntity.ok(listOfStateResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteState(Integer stateID){
        try {
            Optional<StateMaster> stateMaster = stateMasterRepository.findById(stateID);
            if(stateMaster.isPresent()){
                stateMasterRepository.delete(stateMaster.get());
                ResponseEntity.ok("State has been deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
