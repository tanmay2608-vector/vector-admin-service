package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.CityMaster;
import com.labs.vector.service.admin.model.DistrictMaster;
import lombok.Data;

import java.util.List;

@Data
public class ListOfCityDistrictResponse {
    private List<CityMaster> cityMasters;
    private List<DistrictMaster> districtMasterList;
    private String errorMessage;
}
