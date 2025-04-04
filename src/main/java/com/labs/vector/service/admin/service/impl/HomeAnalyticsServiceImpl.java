package com.labs.vector.service.admin.service.impl;

import com.labs.vector.service.admin.dto.response.ListOfVectorAppUserResponse;
import com.labs.vector.service.admin.dto.response.RevenueSalesDetailsResponse;
import com.labs.vector.service.admin.model.UserDetailsEntity;
import com.labs.vector.service.admin.repository.UserDetailsRepository;
import com.labs.vector.service.admin.service.HomeAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class HomeAnalyticsServiceImpl implements HomeAnalyticsService {

    private static final Logger logger = LoggerFactory.getLogger(HomeAnalyticsServiceImpl.class);

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public ResponseEntity<?> getRevenueSalesDetails(Date currentMonthStart, Date currentMonthEnd) {
        Object[] currentMonthData = userDetailsRepository.getTotalRevenueAndCountsForMonth(currentMonthStart, currentMonthEnd);
        Object[] prevMonthData = userDetailsRepository.getTotalRevenueAndCountsForMonth(currentMonthStart, currentMonthEnd);

        Double currentRevenue = (Double) currentMonthData[0];
        Long currentSubscriptions = (Long) currentMonthData[1];
        Long currentSales = (Long) currentMonthData[2];
        Long currentActiveUsers = (Long) currentMonthData[3];

        // Extract data from previous month
        Double prevRevenue = (Double) prevMonthData[0];
        Long prevSubscriptions = (Long) prevMonthData[1];
        Long prevSales = (Long) prevMonthData[2];
        Long prevActiveUsers = (Long) prevMonthData[3];

        double revenuePercentChange = calculatePercentChange(prevRevenue, currentRevenue);
        double subscriptionsPercentChange = calculatePercentChange((double) prevSubscriptions, (double) currentSubscriptions);
        double salesPercentChange = calculatePercentChange((double) prevSales, (double) currentSales);
        double activeUsersPercentChange = calculatePercentChange((double) prevActiveUsers, (double) currentActiveUsers);

        RevenueSalesDetailsResponse revenueSalesDetailsResponse = new RevenueSalesDetailsResponse(currentRevenue, revenuePercentChange,
                currentSubscriptions, subscriptionsPercentChange,
                currentSales, salesPercentChange,
                currentActiveUsers, activeUsersPercentChange);

        return ResponseEntity.ok(revenueSalesDetailsResponse);
    }

    @Override
    public ResponseEntity<?> getMonthlySalesData(Date startDate, Date endDate) {
        try{
            Optional<List<Object[]>> userDetailsOptional = userDetailsRepository.countUsersByTypeAndMonth(startDate,endDate);
            Map<String, Map<String, Long>> userCounts = new HashMap<>();

            for(Object[] row : userDetailsOptional.get()){
                int year = ((Number) row[0]).intValue();
                int month = ((Number) row[1]).intValue();
                String userType = ((Enum<?>) row[2]).name();
                long count = ((Number) row[3]).longValue();

                String yearMonthKey = year + "-" + month;
                userCounts.putIfAbsent(yearMonthKey, new HashMap<>());
                userCounts.get(yearMonthKey).put(userType, count);
            }
            return ResponseEntity.ok(userCounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getRecentUsers(Date startDate, Date endDate) {
        Optional<List<UserDetailsEntity>> userDetailsOptional = userDetailsRepository.findByCreatedAtBetween(startDate,endDate);
        ListOfVectorAppUserResponse userResponse = new ListOfVectorAppUserResponse();
        userResponse.setUserDetailsEntities(userDetailsOptional.get());
        return ResponseEntity.ok(userResponse);
    }

    private double calculatePercentChange(Double prevValue, Double currentValue) {
        if (prevValue == 0) {
            return currentValue == 0 ? 0 : 100; // If previous value is 0 and current is non-zero, 100% increase
        }
        return ((currentValue - prevValue) / prevValue) * 100;
    }
}
