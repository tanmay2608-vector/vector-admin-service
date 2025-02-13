package com.labs.vector.service.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {
    private Integer cityID;
    private String cityName;
    private Integer sortOrder;
    private String isDefault;
    private Integer stateID;
    private Integer countryID;
    private String countryName;
    private String pincode;
    private String stateName;

}
