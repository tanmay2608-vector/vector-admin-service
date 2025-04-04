package com.labs.vector.service.admin.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEventRequest {
    private Integer eventId;
    private String eventName;
    private String description;
    private String isActive;
    private String eventDate;
}
