package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.CountryMaster;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ListOfCountryResponse {
    private List<CountryMaster> countryMasters;
    private String errorMessage;
}
