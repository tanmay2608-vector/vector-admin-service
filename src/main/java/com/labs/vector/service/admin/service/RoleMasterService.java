package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateRoleRequest;
import com.labs.vector.service.admin.dto.request.MapUserRolesReqest;
import org.springframework.http.ResponseEntity;

public interface RoleMasterService {

    ResponseEntity<?> getAllVectorRoles();
    ResponseEntity<?> createRole(CreateRoleRequest createRoleRequest);
    ResponseEntity<?> makeInactiveRole(Integer roleID);
}
