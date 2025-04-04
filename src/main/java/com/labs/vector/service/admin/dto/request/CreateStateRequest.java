package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateStateRequest {
    private Integer stateID;
    private String stateName;
    private Integer sortOrder;
    private String isDefault;
    private Integer countryID;
    private String shortStateName;
}
