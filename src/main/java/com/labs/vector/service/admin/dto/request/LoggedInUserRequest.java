package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class LoggedInUserRequest {
    private String userName;
    private String password;
}
