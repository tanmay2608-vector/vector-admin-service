package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.RegionMaster;
import com.labs.vector.service.admin.model.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionMasterRepository extends JpaRepository<RegionMaster, Integer> {

    Optional<RegionMaster> findByRegionNameAndCityID(Integer cityID, String regionName);

    Optional<RegionMaster> findByIsDefault(String isDefault);

    List<RegionMaster> findByCityID(Integer cityID);

}
