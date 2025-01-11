package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Entity
@Table(name = "role_master")
public class RoleMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "created_by_id")
    private Integer createdById;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private Timestamp createdTime = Timestamp.from(Instant.now());
}
