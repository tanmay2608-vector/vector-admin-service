package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String userName;
    private String password;
    private String conformPassword;
    private String fullName;
}
