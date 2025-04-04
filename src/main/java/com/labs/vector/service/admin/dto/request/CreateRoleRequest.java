package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateRoleRequest {
    private Integer roleID;
    private String roleName;
    private String description;
    private String isActive;
}
