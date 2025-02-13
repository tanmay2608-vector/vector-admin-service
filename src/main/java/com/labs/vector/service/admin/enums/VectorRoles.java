package com.labs.vector.service.admin.enums;

import lombok.Getter;

@Getter
public enum VectorRoles {
    SUPER_ADMIN("SUPER_ADMIN"),
    ADMIN("ADMIN");

    private final String vectorRole;
    VectorRoles(String role){this.vectorRole = role;}

}
