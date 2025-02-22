package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.CreateActivityRequest;
import com.labs.vector.service.admin.dto.request.CreateEventRequest;
import com.labs.vector.service.admin.dto.request.CreateGoalRequest;
import com.labs.vector.service.admin.dto.request.CreateTopicRequest;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/vector-service/v1/activity-setup")
@Tag(name = "Acticity Setup", description = "Activity Setup Api")
public class ActivityController {
    /*------------------------------ Activity APIs -------------------------------------------*/
    @PostMapping("/createUpdateActivity")
    public ResponseEntity<?> createUpdateActivity(@Valid @RequestBody CreateActivityRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllActivities")
    public ResponseEntity<?> getAllActivities() {
        return null;
    }

    @DeleteMapping("/deleteActivity/{activityId}")
    public ResponseEntity<?> deleteActivity(@PathVariable Integer activityId) {
        return null;
    }

    /*------------------------------ Goal APIs -------------------------------------------*/
    @PostMapping("/createUpdateGoal")
    public ResponseEntity<?> createUpdateGoal(@Valid @RequestBody CreateGoalRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllGoals")
    public ResponseEntity<?> getAllGoals() {
        return null;
    }

    @DeleteMapping("/deleteGoal/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable Integer goalId) {
        return null;
    }

    /*------------------------------ Event APIs -------------------------------------------*/
    @PostMapping("/createUpdateEvent")
    public ResponseEntity<?> createUpdateEvent(@Valid @RequestBody CreateEventRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<?> getAllEvents() {
        return null;
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer eventId) {
        return null;
    }
}
