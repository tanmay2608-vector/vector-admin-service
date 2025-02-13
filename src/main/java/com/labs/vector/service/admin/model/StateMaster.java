package com.labs.vector.service.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "STATE_MASTER")
public class StateMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id", updatable = false, nullable = false)
    private Integer stateID;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_default")
    private String isDefault;

    @Column(name = "country_id")
    private Integer countryID;

    @Column(name = "short_state_master")
    private String shortStateName;
}
