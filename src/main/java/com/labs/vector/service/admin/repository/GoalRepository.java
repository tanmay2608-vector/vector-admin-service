package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal,Integer> {

    Optional<Goal> findByGoalName(String goalName);
}
