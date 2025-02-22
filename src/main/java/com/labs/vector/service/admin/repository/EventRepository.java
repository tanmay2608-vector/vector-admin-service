package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findByEventName(String eventName);
}
