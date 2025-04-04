package com.labs.vector.service.admin.model;

import com.labs.vector.service.admin.enums.Gender;
import com.labs.vector.service.admin.enums.StudentStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_DETAILS")
@Data
@ToString
public class UserDetailsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_details_id", updatable = false, nullable = false)
    private Long userDetailsId;

    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "dob")
    private String dob; //dd-mm-yyyy

    @Column(name = "apaar_id")
    private String apaarId;

    @Column(name = "address")
    private String address;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "long_term_goal")
    private String longTermGoal;

    @Column(name = "short_term_goal")
    private String shortTermGoal;

    @Column(name = "aim")
    private String aim;

    @Column(name = "sport_intrested")
    private String sportIntrested;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status")
    private StudentStatusEnum studentStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
