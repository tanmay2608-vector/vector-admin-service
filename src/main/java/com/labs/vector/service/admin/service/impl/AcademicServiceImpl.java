package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.*;
import com.labs.vector.service.admin.model.*;
import com.labs.vector.service.admin.model.Class;
import com.labs.vector.service.admin.repository.*;
import com.labs.vector.service.admin.service.AcademicService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class AcademicServiceImpl implements AcademicService {

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private TopicRepository topicRepository;

    /*------------------------------ Academic Year APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateAcademicYear(CreateAcademicYearRequest createAcademicYearRequest) {
        try {
            if (createAcademicYearRequest.getAcademicYearID() != null && createAcademicYearRequest.getAcademicYearID() == 0) {
                Optional<AcademicYear> existingAcademicYear = academicYearRepository.findByYear(createAcademicYearRequest.getYear());
                if (existingAcademicYear.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Academic Year already exists",
                            "Please choose a different academic year."
                    );
                }
            }

            AcademicYear academicYear = new AcademicYear();
            if (createAcademicYearRequest.getAcademicYearID() > 0) {
                Optional<AcademicYear> existingYear = academicYearRepository.findById(createAcademicYearRequest.getAcademicYearID());
                if (existingYear.isPresent()) {
                    academicYear = existingYear.get();
                }
            }
            academicYear.setYear(createAcademicYearRequest.getYear());
            academicYear.setIsActive(createAcademicYearRequest.getIsActive());

            academicYearRepository.save(academicYear);
            return ResponseEntity.ok(academicYear);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllAcademicYear() {
        try {
            List<AcademicYear> academicYears = academicYearRepository.findAll();
            return ResponseEntity.ok(academicYears);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAcademicYear(Integer academicYearID) {
        try {
            Optional<AcademicYear> academicYear = academicYearRepository.findById(academicYearID);
            if (academicYear.isPresent()) {
                academicYearRepository.deleteById(academicYearID);
                return ResponseEntity.ok("Academic Year deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------------ Class APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateClass(CreateClassRequest createClassRequest) {
        try {
            if (createClassRequest.getClassId() != null && createClassRequest.getClassId() == 0) {
                Optional<Class> existingClass = classRepository.findByClassName(createClassRequest.getClassName());
                if (existingClass.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Class Name already exists",
                            "Please choose a different class name."
                    );
                }
            }
            Class classEntity = new Class();
            if (createClassRequest.getClassId() > 0) {
                Optional<Class> existingClass = classRepository.findById(createClassRequest.getClassId());
                if (existingClass.isPresent()) {
                    classEntity = existingClass.get();
                }
            }
            classEntity.setClassName(createClassRequest.getClassName());
            classEntity.setIsActive(createClassRequest.getIsActive());

            classRepository.save(classEntity);
            return ResponseEntity.ok(classEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllClasses() {
        try {
            List<Class> classes = classRepository.findAll();
            return ResponseEntity.ok(classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteClass(Integer classID) {
        try {
            Optional<Class> classEntity = classRepository.findById(classID);
            if (classEntity.isPresent()) {
                classRepository.deleteById(classID);
                return ResponseEntity.ok("Class deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------------ Subject APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateSubject(CreateSubjectRequest createSubjectRequest) {
        try {
            if (createSubjectRequest.getSubjectId() != null && createSubjectRequest.getSubjectId() == 0) {
                Optional<Subject> existingSubject = subjectRepository.findBySubjectName(createSubjectRequest.getSubjectName());
                if (existingSubject.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Subject Name already exists",
                            "Please choose a different subject name."
                    );
                }
            }
            Subject subject = new Subject();
            if (createSubjectRequest.getSubjectId() > 0) {
                Optional<Subject> existingSubject = subjectRepository.findById(createSubjectRequest.getSubjectId());
                if (existingSubject.isPresent()) {
                    subject = existingSubject.get();
                }
            }
            subject.setSubjectName(createSubjectRequest.getSubjectName());
            subject.setIsActive(createSubjectRequest.getIsActive());

            subjectRepository.save(subject);
            return ResponseEntity.ok(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllSubjects() {
        try {
            List<Subject> subjects = subjectRepository.findAll();
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSubject(Integer subjectID) {
        try {
            Optional<Subject> subject = subjectRepository.findById(subjectID);
            if (subject.isPresent()) {
                subjectRepository.deleteById(subjectID);
                return ResponseEntity.ok("Subject deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------------ Category APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateCategory(CreateCategoryRequest createCategoryRequest) {
        try {
            if (createCategoryRequest.getCategoryId() != null && createCategoryRequest.getCategoryId() == 0) {
                Optional<Category> existingCategory = categoryRepository.findByCategoryName(createCategoryRequest.getCategoryName());
                if (existingCategory.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Category Name already exists",
                            "Please choose a different category name."
                    );
                }
            }

            Category category = new Category();
            if (createCategoryRequest.getCategoryId() > 0) {
                Optional<Category> existingCategory = categoryRepository.findById(createCategoryRequest.getCategoryId());
                if (existingCategory.isPresent()) {
                    category = existingCategory.get();
                }
            }
            category.setCategoryName(createCategoryRequest.getCategoryName());
            category.setIsActive(createCategoryRequest.getIsActive());

            categoryRepository.save(category);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCategory(Integer categoryID) {
        try {
            Optional<Category> category = categoryRepository.findById(categoryID);
            if (category.isPresent()) {
                categoryRepository.deleteById(categoryID);
                return ResponseEntity.ok("Category deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------------ Course APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateCourse(CreateCourseRequest createCourseRequest) {
        try {
            if (createCourseRequest.getCourseId() != null && createCourseRequest.getCourseId() == 0) {
                Optional<Course> existingCourse = courseRepository.findByCourseName(createCourseRequest.getCourseName());
                if (existingCourse.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Course Name already exists",
                            "Please choose a different course name."
                    );
                }
            }

            Course course = new Course();
            if (createCourseRequest.getCourseId() > 0) {
                Optional<Course> existingCourse = courseRepository.findById(createCourseRequest.getCourseId());
                if (existingCourse.isPresent()) {
                    course = existingCourse.get();
                }
            }
            course.setCourseName(createCourseRequest.getCourseName());
            course.setIsActive(createCourseRequest.getIsActive());

            courseRepository.save(course);
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCourses() {
        try {
            List<Course> courses = courseRepository.findAll();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCourse(Integer courseID) {
        try {
            Optional<Course> course = courseRepository.findById(courseID);
            if (course.isPresent()) {
                courseRepository.deleteById(courseID);
                return ResponseEntity.ok("Course deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------------ Semester APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateSemester(CreateSemesterRequest createSemesterRequest) {
        try {
            if (createSemesterRequest.getSemesterId() != null && createSemesterRequest.getSemesterId() == 0) {
                Optional<Semester> existingSemester = semesterRepository.findBySemesterName(createSemesterRequest.getSemesterName());
                if (existingSemester.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Semester Name already exists",
                            "Please choose a different semester name."
                    );
                }
            }
            Semester semester = new Semester();
            if (createSemesterRequest.getSemesterId() > 0) {
                Optional<Semester> existingSemester = semesterRepository.findById(createSemesterRequest.getSemesterId());
                if (existingSemester.isPresent()) {
                    semester = existingSemester.get();
                }
            }
            semester.setSemesterName(createSemesterRequest.getSemesterName());
            semester.setIsActive(createSemesterRequest.getIsActive());

            semesterRepository.save(semester);
            return ResponseEntity.ok(semester);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Semester");
        }
    }

    @Override
    public ResponseEntity<?> getAllSemesters() {
        try {
            List<Semester> semesters = semesterRepository.findAll();
            return ResponseEntity.ok(semesters);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching Semesters");
        }
    }

    @Override
    public ResponseEntity<?> deleteSemester(Integer semesterId) {
        try {
            Optional<Semester> semester = semesterRepository.findById(semesterId);
            if (semester.isPresent()) {
                semesterRepository.deleteById(semesterId);
                return ResponseEntity.ok("Semester deleted successfully!");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Semester not found!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Semester");
        }
    }

    /*------------------------------ Topic APIs -------------------------------------------*/
    @Override
    public ResponseEntity<?> createUpdateTopic(CreateTopicRequest createTopicRequest) {
        try {
            if (createTopicRequest.getTopicId() != null && createTopicRequest.getTopicId() == 0) {
                Optional<Topic> existingTopic = topicRepository.findByTopicName(createTopicRequest.getTopicName());
                if (existingTopic.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: Topic Name already exists",
                            "Please choose a different topic name."
                    );
                }
            }
            Topic topic = new Topic();
            if (createTopicRequest.getTopicId() > 0) {
                Optional<Topic> existingTopic = topicRepository.findById(createTopicRequest.getTopicId());
                if (existingTopic.isPresent()) {
                    topic = existingTopic.get();
                }
            }
            topic.setTopicName(createTopicRequest.getTopicName());
            topic.setIsActive(createTopicRequest.getIsActive());

            topicRepository.save(topic);
            return ResponseEntity.ok(topic);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Topic");
        }
    }

    @Override
    public ResponseEntity<?> getAllTopics() {
        try {
            List<Topic> topics = topicRepository.findAll();
            return ResponseEntity.ok(topics);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching Topics");
        }
    }

    @Override
    public ResponseEntity<?> deleteTopic(Integer topicId) {
        try {
            Optional<Topic> topic = topicRepository.findById(topicId);
            if (topic.isPresent()) {
                topicRepository.deleteById(topicId);
                return ResponseEntity.ok("Topic deleted successfully!");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Topic not found!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Topic");
        }
    }

}
