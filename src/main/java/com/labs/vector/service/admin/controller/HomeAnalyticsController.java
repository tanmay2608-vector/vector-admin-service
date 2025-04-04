package com.labs.vector.service.admin.controller;

import com.labs.vector.service.admin.service.impl.HomeAnalyticsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Validated
@RequestMapping("api/admin/vector-service/v1/home-analytics")
@Tag(name = "Home analytics api", description = "Revenue and sales analytics controller api's")
public class HomeAnalyticsController {
    private static final Logger logger = LoggerFactory.getLogger(HomeAnalyticsController.class);

    @GetMapping("/revenueSalesDetails")
    public ResponseEntity<?> getRevenueSalesDetails(@RequestParam("currentMonthStart") Date currentMonthStart, @RequestParam("currentMonthEnd") Date currentMonthEnd){
        logger.trace("Loding Revenue Sales Details with revenueSalesDetails currentMonthStart {} - currentMonthEnd {} ",currentMonthStart,currentMonthEnd);
        return null;
    }

    @GetMapping("/monthlySales")
    public ResponseEntity<?> getMonthlySalesData(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate){
        return null;
    }

    @GetMapping("recentUsers")
    public ResponseEntity<?> getRecentUsers(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate){
        return null;
    }
}
