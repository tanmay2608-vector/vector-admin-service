package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
