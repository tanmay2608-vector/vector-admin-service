package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.CountryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryMasterRepository extends JpaRepository<CountryMaster, Integer> {

    Optional<CountryMaster> findByCountryName(String countryName);

    Optional<CountryMaster> findByIsDefault(String isDefault);
}
