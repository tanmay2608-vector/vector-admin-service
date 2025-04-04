package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class CountryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id",updatable = false, nullable = false)
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "phone_length")
    private Integer phoneLength;

    @Column(name = "pincode_length")
    private Integer pincodeLength;

    @Column(name = "is_default")
    private String isDefault;

    @Column(name = "additional_info")
    private String additionalInfo;
}
