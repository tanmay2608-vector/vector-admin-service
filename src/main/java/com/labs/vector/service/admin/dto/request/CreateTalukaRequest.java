package com.labs.vector.service.admin.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTalukaRequest {
    private Integer talukaID;
    private String talukaName;
    private Integer districtID;
    private String districtName;
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
