package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class,Integer> {
    Optional<Class> findByClassName(String className);
}
