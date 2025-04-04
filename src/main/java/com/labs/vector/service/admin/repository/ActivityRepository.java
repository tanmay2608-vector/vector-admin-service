package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    Optional<Activity> findByActivityName(String activityName);
}
