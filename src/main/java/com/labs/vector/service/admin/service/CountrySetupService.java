package com.labs.vector.service.admin.service;

import com.labs.vector.service.admin.dto.request.CreateCountryRequest;
import org.springframework.http.ResponseEntity;

public interface CountrySetupService {
    ResponseEntity<?> createUpdateCountry(CreateCountryRequest createCountryRequest);
    ResponseEntity<?> getAllCountry();
    ResponseEntity<?> deleteCountry(Integer countryID);
}
