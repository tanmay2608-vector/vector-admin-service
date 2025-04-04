package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateSchoolRequest;
import com.labs.vector.service.admin.dto.request.CreateUniversityRequest;
import org.springframework.http.ResponseEntity;

public interface InstitutionService {
    ResponseEntity<?> createUpdateSchool(CreateSchoolRequest createSchoolRequest);
    ResponseEntity<?> getAllSchools();
    ResponseEntity<?> deleteSchool(Integer schoolId);

    ResponseEntity<?> createUpdateUniversity(CreateUniversityRequest createUniversityRequest);
    ResponseEntity<?> getAllUniversities();
    ResponseEntity<?> deleteUniversity(Integer universityId);
}
