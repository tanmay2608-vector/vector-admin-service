package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Integer> {
    Optional<University> findByUniversityName(String universityName);

}
