package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.dto.request.*;
import com.labs.vector.service.admin.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    /*------------------------------ Academic Year Apis -------------------------------------------*/
    @PostMapping("/ceateUpdateAcademicYear")
    public ResponseEntity<?> createUpdateAcademicYear(@Valid @RequestBody CreateAcademicYearRequest createAcademicYearRequest, BindingResult result){
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
        return null;
    }

    @DeleteMapping("/deleteAcademicYear/{academicYearID}")
    public ResponseEntity<?> deleteAcademicYear(@PathVariable Integer academicYearID){
        return null;
    }

    /*------------------------------ Class APIs -------------------------------------------*/
    @PostMapping("/createUpdateClass")
    public ResponseEntity<?> createUpdateClass(@Valid @RequestBody CreateClassRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllClasses")
    public ResponseEntity<?> getAllClasses() {
        return null;
    }

    @DeleteMapping("/deleteClass/{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable Integer classId) {
        return null;
    }

    /*------------------------------ Subject APIs -------------------------------------------*/
    @PostMapping("/createUpdateSubject")
    public ResponseEntity<?> createUpdateSubject(@Valid @RequestBody CreateSubjectRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllSubjects")
    public ResponseEntity<?> getAllSubjects() {
        return null;
    }

    @DeleteMapping("/deleteSubject/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer subjectId) {
        return null;
    }

    /*------------------------------ Category APIs -------------------------------------------*/
    @PostMapping("/createUpdateCategory")
    public ResponseEntity<?> createUpdateCategory(@Valid @RequestBody CreateCategoryRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories() {
        return null;
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        return null;
    }

    /*------------------------------ Course APIs -------------------------------------------*/
    @PostMapping("/createUpdateCourse")
    public ResponseEntity<?> createUpdateCourse(@Valid @RequestBody CreateCourseRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses() {
        return null;
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        return null;
    }

    /*------------------------------ Semester APIs -------------------------------------------*/
    @PostMapping("/createUpdateSemester")
    public ResponseEntity<?> createUpdateSemester(@Valid @RequestBody CreateSemesterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllSemesters")
    public ResponseEntity<?> getAllSemesters() {
        return null;
    }

    @DeleteMapping("/deleteSemester/{semesterId}")
    public ResponseEntity<?> deleteSemester(@PathVariable Integer semesterId) {
        return null;
    }

    /*------------------------------ Topic APIs -------------------------------------------*/
    @PostMapping("/createUpdateTopic")
    public ResponseEntity<?> createUpdateTopic(@Valid @RequestBody CreateTopicRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Validation Error",
                    result.getFieldError().getDefaultMessage(),
                    "Please correct the input fields as per the validation rules.");
        }
        return null;
    }

    @GetMapping("/getAllTopics")
    public ResponseEntity<?> getAllTopics() {
        return null;
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer topicId) {
        return null;
    }

}
