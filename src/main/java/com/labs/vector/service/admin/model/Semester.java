package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id", nullable = false, updatable = false)
    private Integer semesterID;

    @Column(name = "semester_name", nullable = false, length = 50)
    private String semesterName;

    @Column(name = "course_id", nullable = false)
    private Integer courseID; // Linking Semester to Course

    @Column(name = "duration", nullable = false, length = 50)
    private String duration;

    @Column(name = "is_active", nullable = false, length = 10)
    private String isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}

