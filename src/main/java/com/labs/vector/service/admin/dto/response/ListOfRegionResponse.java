package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.RegionMaster;
import lombok.Data;
import java.util.List;

@Data
public class ListOfRegionResponse {
    private List<RegionMaster> regionMasters;
    private String errorMessage;
}
