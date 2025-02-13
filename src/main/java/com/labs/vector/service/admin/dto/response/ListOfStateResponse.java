package com.labs.vector.service.admin.dto.response;

import com.labs.vector.service.admin.model.StateMaster;
import lombok.Data;

import java.util.List;

@Data
public class ListOfStateResponse {

    List<StateMaster> stateMasters;
    String errorMessage;
}
