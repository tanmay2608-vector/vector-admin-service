package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false, updatable = false)
    private Integer courseID;

    @Column(name = "course_name", nullable = false, unique = true, length = 100)
    private String courseName;

    @Column(name = "university_id")
    private Integer universityID;

    @Column(name = "duration", nullable = false, length = 50)
    private String duration;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active", nullable = false, length = 10)
    private String isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

