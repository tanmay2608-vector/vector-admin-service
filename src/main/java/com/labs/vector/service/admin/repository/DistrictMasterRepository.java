package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.DistrictMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictMasterRepository extends JpaRepository<DistrictMaster,Integer> {

    Optional<DistrictMaster> findByStateIDAndDistrictName(Integer stateID, String districtName);

    Optional<List<DistrictMaster>> findByStateID(Integer stateID);

    Optional<DistrictMaster> findByIsDefault(String isDefault);
}
