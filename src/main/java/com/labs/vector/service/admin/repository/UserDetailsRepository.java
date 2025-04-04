package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {
    @Query("SELECT u FROM UserDetailsEntity u WHERE u.userId = :userId")
    List<UserDetailsEntity> findAllByUserId(@Param("userId") Integer userId);

//    @Query("SELECT u FROM UserDetailsEntity u WHERE u.userId = :userId")
//    Optional<UserDetailsEntity> findByUserId(Long userDetailsId);

    @Query("SELECT u FROM UserDetailsEntity u WHERE u.userId = :userId")
    Optional<UserDetailsEntity> findByUser(@Param("userId") Integer userId);

    UserDetailsEntity findByUserId(Long userId);

    Optional<List<UserDetailsEntity>> findByCreatedAtBetween(Date startDate, Date endDate);

    // Custom query to get user counts by B2B and B2C types grouped by month in a specific date range
    @Query("SELECT EXTRACT(YEAR FROM u.date) AS year, EXTRACT(MONTH FROM u.date) AS month, u.userType, COUNT(u) " +
            "FROM UserDetailsEntity u " +
            "WHERE u.date BETWEEN :startDate AND :endDate " +
            "GROUP BY EXTRACT(YEAR FROM u.date), EXTRACT(MONTH FROM u.date), u.userType " +
            "ORDER BY year, month, u.userType")
    Optional<List<Object[]>> countUsersByTypeAndMonth(Date startDate, Date endDate);

    // Fetch total revenue, subscriptions, sales, and active users for a specific month
    @Query("SELECT SUM(u.revenue), COUNT(u.subscription), COUNT(u.sales), COUNT(u) " +
            "FROM UserDetail u " +
            "WHERE u.date BETWEEN :startDate AND :endDate")
    Object[] getTotalRevenueAndCountsForMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
