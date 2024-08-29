package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.AreaMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaMasterRepository extends JpaRepository<AreaMaster, Long> {
    List<AreaMaster> findByCompanyIdAndStatusAndSiteId(Long outletId, boolean b, Long id);

    List<AreaMaster> findByCompanyIdAndStatusAndSiteIsNull(Long outletId, boolean b);

    AreaMaster findByIdAndStatus(long id, boolean b);
}
