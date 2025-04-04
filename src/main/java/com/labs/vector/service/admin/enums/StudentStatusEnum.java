package com.labs.vector.service.admin.enums;

import lombok.Getter;

@Getter
public enum StudentStatusEnum {
    SCHOOL("SCHOOL"), //5-10
    UNIVERSITY("UNIVERSITY"), // after 12
    JR_COLLEGE("JR_COLLEGE"), // 11-12
    ENTRANCE("ENTRANCE"),
    OTHERS("OTHERS");// competative

    private final String status;

    StudentStatusEnum(String status) {
        this.status = status;
    }
}
