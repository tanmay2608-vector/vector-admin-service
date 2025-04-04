package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateRoleRequest;
import com.labs.vector.service.admin.dto.request.MapUserRolesReqest;
import com.labs.vector.service.admin.service.RoleMasterService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/admin/vector-service/v1/role-manager")
public class RoleManagementController {

    @Autowired
    private RoleMasterService roleMasterService;

    @GetMapping("/vector-roles")
    public ResponseEntity<?> getAllVectorRoles(){
        return roleMasterService.getAllVectorRoles();
    }

    @PreAuthorize("SUPER_ADMIN")
    @PostMapping("/createRole")
    public ResponseEntity<?> createRole(HttpServletRequest request, @Valid @RequestBody CreateRoleRequest createRoleRequest, BindingResult result){
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }

        return null;
    }

    @PostMapping("/makeInactiveRole/{roleID}")
    public ResponseEntity<?> makeInactiveRole(@PathVariable("roleID") Integer roleID){
        return roleMasterService.makeInactiveRole(roleID);
    }

//    @PreAuthorize("SUPER_ADMIN")
//    @PostMapping("map-user-roles")
//    public ResponseEntity<?> mapUserRoles(HttpServletRequest request, @Valid @RequestBody MapUserRolesReqest mapUserRolesReqest, BindingResult result){
//        String userId = (String) request.getAttribute("userID");
//
//        if(result.hasErrors()){
//            ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST,
//                    "Validation Error",
//                    result.getFieldError().getDefaultMessage(),
//                    "Please correct the input fields as per the validation rules."
//            );
//        }
//        return roleMasterService.mapUserRoles(mapUserRolesReqest, Integer.parseInt(userId));
//    }
}
