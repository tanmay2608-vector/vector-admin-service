package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester,Integer> {
    Optional<Semester> findBySemesterName(String semesterName);
}
