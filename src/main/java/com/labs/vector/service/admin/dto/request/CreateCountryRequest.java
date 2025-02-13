package com.labs.vector.service.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCountryRequest {
    private Integer countryId;
    private String country;
    private String countryCode;
    private Integer sortOrder;
    private Integer phoneLength;
    private Integer pincodeLength;
    private String isDefault;
    private String additionalInfo;
}
