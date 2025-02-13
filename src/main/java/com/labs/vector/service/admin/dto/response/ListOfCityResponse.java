package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.CityMaster;
import lombok.Data;

import java.util.List;

@Data
public class ListOfCityResponse {
    private List<CityMaster> cityMasters;
    private String errorMessage;
}
