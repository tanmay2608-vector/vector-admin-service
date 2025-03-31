package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateRoleRequest;
import com.labs.vector.service.admin.dto.request.MapUserRolesReqest;
import com.labs.vector.service.admin.enums.VectorRoles;
import com.labs.vector.service.admin.model.RoleMaster;
import com.labs.vector.service.admin.model.UserRoleMasterMap;
import com.labs.vector.service.admin.repository.RoleMaterRepository;
import com.labs.vector.service.admin.repository.UserRoleMasterMapRepository;
import com.labs.vector.service.admin.service.RoleMasterService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import com.labs.vector.service.admin.utils.exceptions.UserAlreadyExistsException;
import com.labs.vector.service.admin.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleMasterServiceImpl implements RoleMasterService {
    @Autowired
    private RoleMaterRepository roleMaterRepository;

    @Autowired
    private UserRoleMasterMapRepository userRoleMasterMapRepository;

    @Override
    public ResponseEntity<?> getAllVectorRoles() {
        List<RoleMaster> roleMasterList = roleMaterRepository.findAll();

        if (roleMasterList.isEmpty()) {
            return ResponseUtil.createErrorResponse(
                    HttpStatus.NO_CONTENT,
                    "No roles available",
                    "ERROR: No roles exist in the database",
                    "No data found for roles"
            );
        }

        return ResponseUtil.createSuccessResponse(HttpStatus.OK, roleMasterList);
    }

    @Override
    public ResponseEntity<?> createRole(CreateRoleRequest createRoleRequest) {
        try {
            roleMaterRepository.findByRoleName(createRoleRequest.getRoleName())
                    .orElseThrow(() ->
                            new UserAlreadyExistsException(
                                    "Role already exist",
                                    "400"
                            ));

            RoleMaster roleMaster = new RoleMaster();
            if(createRoleRequest.getRoleID() != null && createRoleRequest.getRoleID() > 0){
                Optional<RoleMaster> existingRole = roleMaterRepository.findById(createRoleRequest.getRoleID());
                if (existingRole.isPresent()){
                    roleMaster = existingRole.get();
                }
            }

            roleMaster.setRoleName(createRoleRequest.getRoleName());
            roleMaster.setRoleDescription(createRoleRequest.getDescription());
            roleMaster.setIsActive(createRoleRequest.getIsActive());
            roleMaterRepository.save(roleMaster);

            return ResponseEntity.ok("Role created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create role ",e);
        }
    }

    @Override
    public ResponseEntity<?> makeInactiveRole(Integer roleID) {
        try{
            RoleMaster roleMaster = roleMaterRepository.findById(roleID)
                    .orElseThrow(()->
                        new UserNotFoundException(
                          "Role not found for roleId: "+roleID,
                          "404"
                        ));

            roleMaster.setIsActive("N");
            roleMaterRepository.save(roleMaster);
            return ResponseEntity.ok("Role deactivated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to make Inactive Role ",e);
        }
    }
}
