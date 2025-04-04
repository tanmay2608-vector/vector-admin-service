package com.labs.vector.service.admin.service;

import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface HomeAnalyticsService {
    ResponseEntity<?> getRevenueSalesDetails(Date currentMonthStart, Date currentMonthEnd);
    ResponseEntity<?> getMonthlySalesData(Date startDate, Date endDate);
    ResponseEntity<?> getRecentUsers(Date startDate, Date endDate);
}
