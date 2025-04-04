package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Optional<Topic> findByTopicName(String topicName);
}
