package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.access_permission_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.access_permissions.SystemMasterModules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemMasterModuleRepository extends JpaRepository<SystemMasterModules, Long> {
    @Query(value = "SELECT * from access_modules_tbl WHERE status=?1 AND parent_module_id IS NULL",
            nativeQuery = true)
    List<SystemMasterModules> findModules(boolean b);

    SystemMasterModules findByIdAndStatus(Long parentModuleId, boolean b);

    List<SystemMasterModules> findByParentModuleIdAndStatus(Long parentModuleId, boolean b);

    List<SystemMasterModules> findByStatus(boolean b);
}
