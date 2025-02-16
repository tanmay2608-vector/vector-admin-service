package com.labs.vector.service.admin.repository;

import com.labs.vector.service.admin.model.VillageMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VillageMasterRepository extends JpaRepository<VillageMaster, Integer> {
    Optional<VillageMaster> findByTalukaIDAndVillageName(Integer talukaID, String villageName);

    Optional<List<VillageMaster>> findByTalukaID(Integer talukaID);

    Optional<VillageMaster> findByIsDefault(String isDefault);
}
