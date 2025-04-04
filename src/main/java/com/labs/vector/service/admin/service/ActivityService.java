package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateActivityRequest;
import com.labs.vector.service.admin.dto.request.CreateEventRequest;
import com.labs.vector.service.admin.dto.request.CreateGoalRequest;
import org.springframework.http.ResponseEntity;

public interface ActivityService {
    ResponseEntity<?> createUpdateActivity(CreateActivityRequest createActivityRequest);
    ResponseEntity<?> getAllActivities();
    ResponseEntity<?> deleteActivity(Integer activityId);

    ResponseEntity<?> createUpdateGoal(CreateGoalRequest createGoalRequest);
    ResponseEntity<?> getAllGoals();
    ResponseEntity<?> deleteGoal(Integer goalId);

    ResponseEntity<?> createUpdateEvent(CreateEventRequest createEventRequest);
    ResponseEntity<?> getAllEvents();
    ResponseEntity<?> deleteEvent(Integer eventId);
}
