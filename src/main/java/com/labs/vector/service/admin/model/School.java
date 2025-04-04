package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id", nullable = false, updatable = false)
    private Integer schoolId;

    @Column(name = "school_name", nullable = false, unique = true, length = 100)
    private String schoolName;

    @Column(name = "school_address", nullable = false, length = 255)
    private String schoolAddress;

    @Column(name = "academic_year_id", nullable = false)
    private Integer academicYearID;

    @Column(name = "is_active", nullable = false)
    private String isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

