package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.access_permission_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.access_permissions.SystemAccessPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemAccessPermissionsRepository extends JpaRepository<SystemAccessPermissions,Long> {

    List<SystemAccessPermissions> findByUsersIdAndStatus(Long id, boolean b);
    SystemAccessPermissions findByUsersIdAndStatusAndSystemActionMappingId(Long id, boolean b, Long id1);
    List<SystemAccessPermissions> findByUserRoleIdAndStatus(long roleId, boolean b);
}
