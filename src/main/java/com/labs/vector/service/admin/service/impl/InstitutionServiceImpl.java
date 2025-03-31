package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.request.CreateSchoolRequest;
import com.labs.vector.service.admin.dto.request.CreateUniversityRequest;
import com.labs.vector.service.admin.dto.response.ListOfSchoolResponse;
import com.labs.vector.service.admin.dto.response.ListOfUniversityResponse;
import com.labs.vector.service.admin.model.School;
import com.labs.vector.service.admin.model.University;
import com.labs.vector.service.admin.repository.SchoolRepository;
import com.labs.vector.service.admin.repository.UniversityRepository;
import com.labs.vector.service.admin.service.InstitutionService;
import com.labs.vector.service.admin.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Override
    public ResponseEntity<?> createUpdateSchool(CreateSchoolRequest createSchoolRequest) {
        try{
            if(createSchoolRequest.getSchoolId() != null && createSchoolRequest.getSchoolId() == 0) {
                Optional<School> schoolOptional = schoolRepository.findBySchoolName(createSchoolRequest.getSchoolName());
                if (schoolOptional.isPresent()) {
                    ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: School Name already exists",
                            "Please choose a different city."
                    );
                }
            }

            School school = new School();
            //In case of update state....
            if(createSchoolRequest.getSchoolId() > 0){
                Optional<School> existingSchool = schoolRepository.findById(createSchoolRequest.getSchoolId());
                if(existingSchool.isPresent()) {
                    school = existingSchool.get();
                }
            }

            school.setSchoolName(createSchoolRequest.getSchoolName());
            school.setSchoolAddress(createSchoolRequest.getSchoolAddress());
            school.setAcademicYearID(createSchoolRequest.getAcademicYearID());
            school.setIsActive(createSchoolRequest.getIsActive());

            schoolRepository.save(school);
           return ResponseEntity.ok(school);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create & update school",e);
        }
    }

    @Override
    public ResponseEntity<?> getAllSchools() {
        try {
            List<School> schoolList = schoolRepository.findAll();
            if(schoolList != null && !schoolList.isEmpty()){
                ListOfSchoolResponse listOfSchoolResponse = new ListOfSchoolResponse();
                listOfSchoolResponse.setSchoolList(schoolList);
                return ResponseEntity.ok(listOfSchoolResponse);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"schools not found", "No schools exist ","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all schools",e);
        }
    }

    @Override
    public ResponseEntity<?> deleteSchool(Integer schoolId) {
        try {
            Optional<School> schoolOptional = schoolRepository.findById(schoolId);
            if(schoolOptional.isPresent()){
                schoolRepository.deleteById(schoolId);
                return ResponseEntity.ok("School has been deleted successfully!");
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"School not found", "Invalid school Id : "+schoolId,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete schools",e);
        }
    }

    @Override
    public ResponseEntity<?> createUpdateUniversity(CreateUniversityRequest createUniversityRequest) {
        try {
            if (createUniversityRequest.getUniversityId() != null && createUniversityRequest.getUniversityId() == 0) {
                Optional<University> universityOptional = universityRepository.findByUniversityName(createUniversityRequest.getUniversityName());
                if (universityOptional.isPresent()) {
                    return ResponseUtil.createErrorResponse(
                            HttpStatus.BAD_REQUEST,
                            "Data duplicates",
                            "ERROR: University Name already exists",
                            "Please choose a different university name."
                    );
                }
            }

            University university = new University();
            // In case of update...
            if (createUniversityRequest.getUniversityId() > 0) {
                Optional<University> existingUniversity = universityRepository.findById(createUniversityRequest.getUniversityId());
                if (existingUniversity.isPresent()) {
                    university = existingUniversity.get();
                }
            }

            university.setUniversityName(createUniversityRequest.getUniversityName());
            university.setIsActive(createUniversityRequest.getIsActive());

            universityRepository.save(university);
            return ResponseEntity.ok(university);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to create & Update University",e);
        }
    }

    @Override
    public ResponseEntity<?> getAllUniversities() {
        try {
            List<University> universityList = universityRepository.findAll();
            if (universityList != null && !universityList.isEmpty()) {
                ListOfUniversityResponse listOfUniversityResponse = new ListOfUniversityResponse();
                listOfUniversityResponse.setUniversities(universityList);
                return ResponseEntity.ok(listOfUniversityResponse);
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"Universities not found", "No universities exist","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to get all Universities",e);
        }
    }

    @Override
    public ResponseEntity<?> deleteUniversity(Integer universityId) {
        try {
            Optional<University> universityOptional = universityRepository.findById(universityId);
            if (universityOptional.isPresent()) {
                universityRepository.deleteById(universityId);
                return ResponseEntity.ok("University has been deleted successfully!");
            }else {
                return  ResponseUtil.createErrorResponse(HttpStatus.NO_CONTENT,"University not found", "Invalid university Id "+universityId,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing to delete University",e);
        }
    }

}
