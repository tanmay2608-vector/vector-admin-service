package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.*;
import org.springframework.http.ResponseEntity;

public interface AcademicService {
    ResponseEntity<?> createUpdateAcademicYear(CreateAcademicYearRequest createAcademicYearRequest);
    ResponseEntity<?> getAllAcademicYear();
    ResponseEntity<?> deleteAcademicYear(Integer academicYearID);

    ResponseEntity<?> createUpdateClass(CreateClassRequest createClassRequest);
    ResponseEntity<?> getAllClasses();
    ResponseEntity<?> deleteClass(Integer classID);

    ResponseEntity<?> createUpdateSubject(CreateSubjectRequest createSubjectRequest);
    ResponseEntity<?> getAllSubjects();
    ResponseEntity<?> deleteSubject(Integer subjectID);

    ResponseEntity<?> createUpdateCategory(CreateCategoryRequest createCategoryRequest);
    ResponseEntity<?> getAllCategories();
    ResponseEntity<?> deleteCategory(Integer categoryID);

    ResponseEntity<?> createUpdateCourse(CreateCourseRequest createCourseRequest);
    ResponseEntity<?> getAllCourses();
    ResponseEntity<?> deleteCourse(Integer courseId);

    ResponseEntity<?> createUpdateSemester(CreateSemesterRequest createSemesterRequest);
    ResponseEntity<?> getAllSemesters();
    ResponseEntity<?> deleteSemester(Integer semesterId);

    ResponseEntity<?> createUpdateTopic(CreateTopicRequest createTopicRequest);
    ResponseEntity<?> getAllTopics();
    ResponseEntity<?> deleteTopic(Integer topicId);
}
