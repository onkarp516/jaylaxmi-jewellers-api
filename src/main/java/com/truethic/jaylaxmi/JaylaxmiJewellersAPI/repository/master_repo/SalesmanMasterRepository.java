package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.SalesManMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesmanMasterRepository extends JpaRepository<SalesManMaster, Long> {
    SalesManMaster findByIdAndStatus(long id, boolean b);

    List<SalesManMaster> findByCompanyIdAndStatusAndSiteId(Long outletId, boolean b, Long id);

    List<SalesManMaster> findByCompanyIdAndStatusAndSiteIsNull(Long outletId, boolean b);
}
