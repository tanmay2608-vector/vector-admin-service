package com.labs.vector.service.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_role_master_map")
public class UserRoleMasterMap {

    @Id
    @Column(name = "user_role_master_map_id")
    private Integer userRoleMasterMapID;

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "role_master_id")
    private Integer roleMaterID;

}
