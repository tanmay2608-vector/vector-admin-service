package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.TalukaMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TalukaMasterRepository extends JpaRepository<TalukaMaster, Integer> {

    Optional<TalukaMaster> findByDistrictIDAndTalukaName(Integer districtID, String talukaName);

    Optional<List<TalukaMaster>> findByDistrictID(Integer districtID);

    Optional<TalukaMaster> findByIsDefault(String isDefault);

}
