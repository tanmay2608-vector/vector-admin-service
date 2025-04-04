package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateActivityRequest;
import com.labs.vector.service.admin.dto.request.CreateEventRequest;
import com.labs.vector.service.admin.dto.request.CreateGoalRequest;
import com.labs.vector.service.admin.model.Activity;
import com.labs.vector.service.admin.model.Event;
import com.labs.vector.service.admin.model.Goal;
import com.labs.vector.service.admin.repository.ActivityRepository;
import com.labs.vector.service.admin.repository.EventRepository;
import com.labs.vector.service.admin.repository.GoalRepository;
import com.labs.vector.service.admin.service.ActivityService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ActivityServiceImpl implements ActivityService {
    private static final Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private EventRepository eventRepository;

    /*------------------------------ Activity APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateActivity(CreateActivityRequest createActivityRequest) {
        try {
            if (createActivityRequest.getActivityId() != null && createActivityRequest.getActivityId() == 0) {
                Optional<Activity> existingActivity = activityRepository.findByActivityName(createActivityRequest.getActivityName());
                log.info("Existing Activity: {}",existingActivity.toString());
                if (existingActivity.isPresent()) {
                    log.info("Activity Already exists");
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Activity Name already exists",
                            "Please choose a different activity name."
                    );
                }
            }

            Activity activity = new Activity();
            if (createActivityRequest.getActivityId() > 0) {
                Optional<Activity> existingActivity = activityRepository.findById(createActivityRequest.getActivityId());
                if (existingActivity.isPresent()) {
                    activity = existingActivity.get();
                }
            }

            activity.setActivityName(createActivityRequest.getActivityName());
            activity.setIsActive(createActivityRequest.getIsActive());

            activityRepository.save(activity);
            log.info("Activity saved:{}", activity);
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create & Update Activity",e);
        }
    }

    @Override
    public ResponseEntity<?> getAllActivities() {
        try {
            List<Activity> activityList = activityRepository.findAll();
            if (!activityList.isEmpty()) {
                log.info("Activity List :{}",activityList.toString());
                return ResponseEntity.ok(activityList);
            }else {
                log.info("Acitvity not found");
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT," Activities not found", "No activites exist : ","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all Activity",e);
        }
    }

    @Override
    public ResponseEntity<?> deleteActivity(Integer activityId) {
        try {
            Optional<Activity> activityOptional = activityRepository.findById(activityId);
            log.info("Activity:{}",activityOptional.toString());
            if (activityOptional.isPresent()) {
                activityRepository.deleteById(activityId);
                log.info("Activity Deleted successfully");
                return ResponseEntity.ok("Activity has been deleted successfully!");
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"Activity not found", "Invalid activity Id : "+activityId,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete Activity",e);
        }
    }

    /*------------------------------ Goal APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateGoal(CreateGoalRequest createGoalRequest) {
        try {
            if (createGoalRequest.getGoalId() != null && createGoalRequest.getGoalId() == 0) {
                Optional<Goal> existingGoal = goalRepository.findByGoalName(createGoalRequest.getGoalName());
                log.info("Goal:{}",existingGoal.toString());
                if (existingGoal.isPresent()) {
                    log.info("Goal Name already exists");
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Goal Name already exists",
                            "Please choose a different goal name."
                    );
                }
            }

            Goal goal = new Goal();
            if (createGoalRequest.getGoalId() > 0) {
                Optional<Goal> existingGoal = goalRepository.findById(createGoalRequest.getGoalId());
                if (existingGoal.isPresent()) {
                    goal = existingGoal.get();
                }
            }

            goal.setGoalName(createGoalRequest.getGoalName());
            goal.setIsActive(createGoalRequest.getIsActive());

            goalRepository.save(goal);
            log.info("Goal saved :{}", goal.toString());
            return ResponseEntity.ok(goal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create & update goal",e);
        }
    }

    @Override
    public ResponseEntity<?> getAllGoals() {
        try {
            List<Goal> goalList = goalRepository.findAll();
            if (!goalList.isEmpty()) {
                log.info("Goal List :{}", goalList.toString());
                return ResponseEntity.ok(goalList);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"Goals not found", "No goals exits ","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all goal",e);
        }
    }

    @Override
    public ResponseEntity<?> deleteGoal(Integer goalId) {
        try {
            Optional<Goal> goalOptional = goalRepository.findById(goalId);
            log.info("Goal:{}",goalOptional.toString());
            if (goalOptional.isPresent()) {
                goalRepository.deleteById(goalId);
                log.info("Goal is deleted");
                return ResponseEntity.ok("Goal has been deleted successfully!");
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"Goal not found", "Invalid goal Id : "+goalId,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete goal",e);
        }
    }

    /*------------------------------ Event APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateEvent(CreateEventRequest createEventRequest) {
        try {
            // Check for duplicate Event Name
            if (createEventRequest.getEventId() != null && createEventRequest.getEventId() == 0) {
                Optional<Event> existingEvent = eventRepository.findByEventName(createEventRequest.getEventName());
                log.info("Event:{}",existingEvent.toString());
                if (existingEvent.isPresent()) {
                    log.info("Event is already exists");
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Event Name already exists",
                            "Please choose a different event name."
                    );
                }
            }

            Event event = new Event();
            if (createEventRequest.getEventId() > 0) {
                Optional<Event> existingEvent = eventRepository.findById(createEventRequest.getEventId());
                if (existingEvent.isPresent()) {
                    event = existingEvent.get();
                }
            }

            event.setEventName(createEventRequest.getEventName());
            event.setEventDate(LocalDate.parse(createEventRequest.getEventDate()));
            event.setIsActive(createEventRequest.getIsActive());

            eventRepository.save(event);
            log.info("Event Saved:{}",event);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create & Update Event",e);
        }
    }

    @Override
    public ResponseEntity<?> getAllEvents() {
        try {
            List<Event> eventList = eventRepository.findAll();
            if (!eventList.isEmpty()) {
                log.info("Event List :{}",eventList.toString());
                return ResponseEntity.ok(eventList);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"Events not found", "No Events exist","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all Events",e);
        }
    }

    @Override
    public ResponseEntity<?> deleteEvent(Integer eventId) {
        try {
            Optional<Event> eventOptional = eventRepository.findById(eventId);
            log.info("Event :{}",eventOptional.toString());
            if (eventOptional.isPresent()) {
                eventRepository.deleteById(eventId);
                log.info("Event Deleted Successfully");
                return ResponseEntity.ok("Event has been deleted successfully!");
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"Event not found", "Invalid event Id : "+eventId,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete Events",e);
        }
    }
}
