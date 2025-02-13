package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "REGION_MASTER")
public class RegionMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regionID", updatable = false, nullable = false)
    private int regionID;

    @Column(name = "city_ID")
    private int cityID;

    @Column(name = "city_Name")
    private String cityName;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_default")
    private String isDefault;
}
