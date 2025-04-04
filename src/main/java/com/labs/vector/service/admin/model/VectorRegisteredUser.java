package com.labs.vector.service.admin.model;

import com.labs.vector.service.admin.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "vector_registered_user")
public class VectorRegisteredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vector_user_id")
    private Integer vectorUserID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "name_prefix")
    private String namePrefix;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "user_name_alias")
    private String userNameAlias;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "age")
    private int age;

    @Column(name = "dateOfJoining")
    private Date dateOfJoining;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "countryCode")
    private int countryCode;

    @Column(name = "whatsappCountryCode")
    private Integer whatsappCountryCode;

    @Column(name = "emergencyNumber")
    private String emergencyNumber;

    @Column(name = "emergencyContactPerson")
    private Integer emergencyContactPerson;

    @Column(name = "secondaryPhoneNumber")
    private String secondaryPhoneNumber;

    @Column(name = "whatsappNumber")
    private Integer whatsappNumber;

    @Column(name = "officialEmailId")
    private String officialEmailId;

    @Column(name = "userCode")
    private String userCode;

    @Column(name = "status")
    private String status;

    @Column(name = "userType")
    private String userType;

    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "is_system_generated")
    private String isSystemGenerated;

    @Column(name = "createdByID")
    private Integer createdByID;

    @Column(name = "createdByName")
    private String createdByName;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updatedByID")
    private Integer updatedByID;

    @Column(name = "updatedByName")
    private String updatedByName;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime = LocalDateTime.now();

}
