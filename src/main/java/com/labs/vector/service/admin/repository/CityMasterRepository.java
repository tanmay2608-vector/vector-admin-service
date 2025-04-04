package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.CityMaster;
import com.labs.vector.service.admin.model.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityMasterRepository extends JpaRepository<CityMaster, Integer> {

    Optional<CityMaster> findByCityNameAndStateID(String cityName, Integer stateID);

    Optional<CityMaster> findByIsDefault(String isDefault);

    List<CityMaster> findByStateID(Integer stateID);
}
