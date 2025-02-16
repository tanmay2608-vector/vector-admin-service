package com.labs.vector.service.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDistrictRequest {
    private Integer districtID;
    private String districtName;
    private Integer stateID;
    private String stateName;
    private Integer sortOrder;
    private String isDefault;
    private String isActive;
    private Integer updatedByID;
    private Integer createByID;
    private String updatedBy;
    private String createdBy;
}
