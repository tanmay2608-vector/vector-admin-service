package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CITY_MASTER")
public class CityMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false, updatable = false)
    private Integer cityID;

    @Column(name = "city_Name")
    private String cityName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_default")
    private String isDefault;

    @Column(name = "state_id")
    private Integer stateID;

    @Column(name = "country_id")
    private Integer countryID;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "pincode")
    private String pincode;
}
