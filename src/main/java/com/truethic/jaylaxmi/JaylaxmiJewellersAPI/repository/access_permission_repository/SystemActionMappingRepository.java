package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.access_permission_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.access_permissions.SystemActionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemActionMappingRepository extends JpaRepository<SystemActionMapping, Long> {

    List<SystemActionMapping> findByStatus(boolean b);

    SystemActionMapping findByIdAndStatus(long mapping_id, boolean b);

    SystemActionMapping findBySystemMasterModulesIdAndStatus(Long mapElement, boolean b);
}
