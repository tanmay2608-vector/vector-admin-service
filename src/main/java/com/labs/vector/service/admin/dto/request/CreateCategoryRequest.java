package com.labs.vector.service.admin.dto.request;

import lombok.Data;

@Data
public class CreateCategoryRequest {
    private Integer categoryId;
    private String categoryName;
    private String isActive;
}

