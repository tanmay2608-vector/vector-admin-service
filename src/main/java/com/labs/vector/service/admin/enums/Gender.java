package com.labs.vector.service.admin.enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHERS("OTHERS");

    private final String gender;
    Gender(String gender){
        this.gender = gender;
    }
}
