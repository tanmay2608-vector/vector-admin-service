package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findBySubjectName(String subjectName);
}
