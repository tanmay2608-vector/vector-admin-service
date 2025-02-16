package com.labs.vector.service.admin.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateVillageRequest {
    private Integer villageID;
    private String villageName;
    private Integer talukaID;
    private String talukaName;
    private Integer sortOrder;
    private String isDefault;
    private String isActive;
    private Integer updatedByID;
    private Integer createByID;
    private String updatedBy;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}