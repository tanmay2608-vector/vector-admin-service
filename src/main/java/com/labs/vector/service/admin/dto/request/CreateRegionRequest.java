package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateRegionRequest {
    private int regionID;
    private int cityID;
    private String regionName;
    private String pinCode;
    private Integer sortOrder;
    private String isDefault;
}
