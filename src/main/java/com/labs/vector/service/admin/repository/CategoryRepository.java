package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategoryName(String categoryName);
}
