package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateActivityRequest;
import com.labs.vector.service.admin.dto.request.CreateEventRequest;
import com.labs.vector.service.admin.dto.request.CreateGoalRequest;
import com.labs.vector.service.admin.dto.request.CreateTopicRequest;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/vector-service/v1/activity-setup")
@Tag(name = "Acticity Setup", description = "Activity Setup Api")
public class ActivityController {
    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);
    /*------------------------------ Activity APIs -------------------------------------------*/
    @PostMapping("/createUpdateActivity")
    public ResponseEntity<?> createUpdateActivity(@Valid @RequestBody CreateActivityRequest request, BindingResult result) {
        log.info("Create or Update activity recived:{}",request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validation rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllActivities")
    public ResponseEntity<?> getAllActivities() {
        log.info("Fetch all activities");
        return null;
    }

    @DeleteMapping("/deleteActivity/{activityId}")
    public ResponseEntity<?> deleteActivity(@PathVariable Integer activityId) {
        log.info("Request to delete Activity with Id ");

        return null;
    }

    /*------------------------------ Goal APIs -------------------------------------------*/
    @PostMapping("/createUpdateGoal")
    public ResponseEntity<?> createUpdateGoal(@Valid @RequestBody CreateGoalRequest request, BindingResult result) {
        log.info("Create or Update goal request :{}", request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validations rules");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllGoals")
    public ResponseEntity<?> getAllGoals() {
        log.info("Fectching all goals.");
        return null;
    }

    @DeleteMapping("/deleteGoal/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable Integer goalId) {
        log.info("Request to delete goal with Id :{}",goalId);
        return null;
    }

    /*------------------------------ Event APIs -------------------------------------------*/
    @PostMapping("/createUpdateEvent")
    public ResponseEntity<?> createUpdateEvent(@Valid @RequestBody CreateEventRequest request, BindingResult result) {
        log.info("Create or Update Event request :{}", request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validations rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<?> getAllEvents() {
        log.info("Fectching all events.");
        return null;
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer eventId) {
        log.info("Request to delete event with Id :{}",eventId);
        return null;
    }
}
