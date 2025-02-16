package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.VillageMaster;
import lombok.Data;

import java.util.List;

@Data
public class ListOfVillageResponse {
    private List<VillageMaster> villageMasters;
    private String errorMessage;
}
