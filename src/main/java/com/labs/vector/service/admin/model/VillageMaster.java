package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "taluka_master")
public class VillageMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "village_id", nullable = false, updatable = false)
    private Integer villageID;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "taluka_id")
    private Integer talukaID;

    @Column(name = "taluka_name")
    private String talukaName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_default")
    private String isDefault;

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "updated_by_id")
    private Integer updatedByID;

    @Column(name = "create_by_id")
    private Integer createByID;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
