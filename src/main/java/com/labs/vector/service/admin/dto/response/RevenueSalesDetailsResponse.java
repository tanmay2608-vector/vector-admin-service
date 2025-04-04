package com.labs.vector.service.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueSalesDetailsResponse {
    private Double totalRevenue;
    private double revenuePercentChange;
    private Long subscriptionsCount;
    private double subscriptionsPercentChange;
    private Long salesCount;
    private double salesPercentChange;
    private Long activeUserCount;
    private double activeUserPercentChange;
}
