package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.VectorRegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VectorRegisteredUserRepository extends JpaRepository<VectorRegisteredUser, Integer> {

    Optional<VectorRegisteredUser> findByUserName(String userName);

    Optional<VectorRegisteredUser> findByUserNameAndPassword(String userName, String password);

    Optional<List<Object>> findAllUser();
}
