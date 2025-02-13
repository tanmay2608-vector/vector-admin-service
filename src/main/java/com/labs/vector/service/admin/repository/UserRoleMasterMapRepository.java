package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.UserRoleMasterMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMasterMapRepository extends JpaRepository<UserRoleMasterMap,Integer> {

    List<UserRoleMasterMap> findByUserIDAndRoleMaterID(Integer UserID, Integer RoleMaterID);

    @Query("DELETE FROM user_role_master_map WHERE user_id = :userIDs")
    void deleteByUserIDs(@Param("userIDs") List<Integer> userIDs);

    void deleteByUserID(Integer userID);
}
