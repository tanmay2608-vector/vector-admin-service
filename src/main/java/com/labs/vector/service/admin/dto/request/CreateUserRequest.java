package com.labs.vector.service.admin.dto.request;

import com.labs.vector.service.admin.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private Integer userID;
    private String namePrefix;
    private String userName;
    private String password;
    private String conformPassword;
    private String fullName;
    private String userNameAlias;
    private Date dob;
    private int age;
    private Date dateOfJoining;
    private String emailId;
    private int countryCode;
    private Integer whatsappCountryCode;
    private Integer phoneNumber;
    private String emergencyNumber;
    private Integer emergencyContactPerson;
    private String secondaryPhoneNumber;
    private Integer whatsappNumber;
    private Gender gender;
    private String officialEmailId;
    private String userCode;
    private String status;
    private String userType;
    private String departmentName;
    private String designation;
    private Integer createdByID;
    private String createdByName;
    private Integer updatedByID;
    private String updatedByName;
    List<Integer> vectorRoleIDList;
}
