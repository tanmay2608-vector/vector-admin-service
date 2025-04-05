package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.*;
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
@Validated
@Tag(name = "Academic Setup", description = "Academic Apis")
@RequestMapping("/api/admin/vector-service/v1/academic-setup")
public class AcademicController {
    private static final Logger log = LoggerFactory.getLogger(AcademicController.class);
    /*------------------------------ Academic Year Apis -------------------------------------------*/
    @PostMapping("/ceateUpdateAcademicYear")
    public ResponseEntity<?> createUpdateAcademicYear(@Valid @RequestBody CreateAcademicYearRequest createAcademicYearRequest, BindingResult result){
        log.info("Create Or Update Academic Year request: {}", createAcademicYearRequest);
        if(result.hasErrors()){
            ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules."
            );
        }
        return null;
    }

    @GetMapping("/getAllAcademicYear")
    public ResponseEntity<?> getAllAcademicYear(){
        log.info("Fetching all academic Year");
        return null;
    }

    @DeleteMapping("/deleteAcademicYear/{academicYearID}")
    public ResponseEntity<?> deleteAcademicYear(@PathVariable Integer academicYearID){
        log.info("Request to delete Academic Year with ID:{}",academicYearID);
        return null;
    }

    /*------------------------------ Class APIs -------------------------------------------*/
    @PostMapping("/createUpdateClass")
    public ResponseEntity<?> createUpdateClass(@Valid @RequestBody CreateClassRequest request, BindingResult result) {
        log.info("Create or Update subject request:{}",request);

        if (result.hasErrors()) {
            log.info("Pleaser Correct the input fields as per the validation rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllClasses")
    public ResponseEntity<?> getAllClasses() {
        log.info("Fetching all classes.");
        return null;
    }

    @DeleteMapping("/deleteClass/{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable Integer classId) {
        log.info("Request to delete Class with classID:{}",classId);
        return null;
    }

    /*------------------------------ Subject APIs -------------------------------------------*/
    @PostMapping("/createUpdateSubject")
    public ResponseEntity<?> createUpdateSubject(@Valid @RequestBody CreateSubjectRequest request, BindingResult result) {
        log.info("Create or Update Subject request recived: {}", request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validations rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllSubjects")
    public ResponseEntity<?> getAllSubjects() {
        log.info("Fetching all Subjects");
        return null;
    }

    @DeleteMapping("/deleteSubject/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer subjectId) {
        log.info("Request to delete Subject with ID:{}",subjectId);
        return null;
    }

    /*------------------------------ Category APIs -------------------------------------------*/
    @PostMapping("/createUpdateCategory")
    public ResponseEntity<?> createUpdateCategory(@Valid @RequestBody CreateCategoryRequest request, BindingResult result) {
        log.info("Create or Update Category request:{}",request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validation rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories() {
        log.info("Fetching all Categories");
        return null;
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        log.info("Request to delete Category with ID:{}",categoryId);
        return null;
    }

    /*------------------------------ Course APIs -------------------------------------------*/
    @PostMapping("/createUpdateCourse")
    public ResponseEntity<?> createUpdateCourse(@Valid @RequestBody CreateCourseRequest request, BindingResult result) {
        log.info("Create or Update Course request:{}",request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validation rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses() {
        log.info("Fetching all Courses");
        return null;
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        log.info("Request to delete course with ID:{}",courseId);
        return null;
    }

    /*------------------------------ Semester APIs -------------------------------------------*/
    @PostMapping("/createUpdateSemester")
    public ResponseEntity<?> createUpdateSemester(@Valid @RequestBody CreateSemesterRequest request, BindingResult result) {
        log.info("Create or Update a Semester request:{}",request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validation rules.");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllSemesters")
    public ResponseEntity<?> getAllSemesters() {
        log.info("Fetching all semetsters");
        return null;
    }

    @DeleteMapping("/deleteSemester/{semesterId}")
    public ResponseEntity<?> deleteSemester(@PathVariable Integer semesterId) {
        log.info("Request to delete semester with ID:{}",semesterId);
        return null;
    }

    /*------------------------------ Topic APIs -------------------------------------------*/
    @PostMapping("/createUpdateTopic")
    public ResponseEntity<?> createUpdateTopic(@Valid @RequestBody CreateTopicRequest request, BindingResult result) {
        log.info("Create or Update topic request :{}",request);
        if (result.hasErrors()) {
            log.info("Please correct the input fields as per the validations rules");
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllTopics")
    public ResponseEntity<?> getAllTopics() {
        log.info("Fetching all Topics");
        return null;
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer topicId) {
        log.info("Request to delete Topic with ID:{}",topicId);
        return null;
    }

}
