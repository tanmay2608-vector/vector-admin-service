package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Optional<Course> findByCourseName(String courseName);
}
