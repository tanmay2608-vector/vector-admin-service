package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    Optional<School> findBySchoolName(String schoolName);
}
