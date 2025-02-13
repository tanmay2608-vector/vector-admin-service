package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMaster, Integer> {

    Optional<StateMaster> findByStateNameAndCountryID(Integer countryID, String stateName);

    Optional<StateMaster> findByIsDefault(String isDefault);

    List<StateMaster> findByCountryID(Integer countryID);
}
