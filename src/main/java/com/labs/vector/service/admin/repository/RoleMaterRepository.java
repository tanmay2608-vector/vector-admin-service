package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleMaterRepository extends JpaRepository<RoleMaster,Integer> {
    Optional<RoleMaster> findByRoleName(String roleName);
}
