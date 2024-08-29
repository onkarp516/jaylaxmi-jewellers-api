package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.TaxMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaxMasterRepository extends JpaRepository<TaxMaster, Long> {

    TaxMaster findByIdAndStatus(long id, boolean b);

    List<TaxMaster> findByCompanyIdAndSiteIdAndStatus(Long id, Long id1, boolean b);

    @Query(
            value = " SELECT * FROM tax_master_tbl WHERE company_id=?1 AND site_id=?2 AND gst_per=?3 AND status=?4", nativeQuery = true
    )
    TaxMaster findDuplicateGSTWithSite(Long outletId, Long branchId, String gst_per, boolean b);

    @Query(
            value = " SELECT * FROM tax_master_tbl WHERE company_id=?1 AND gst_per=?2 AND status=?3 AND site_id IS NULL", nativeQuery = true
    )
    TaxMaster findDuplicateGSTWithCompany(Long id, String gst_per,boolean b);

    List<TaxMaster> findByCompanyIdAndStatusAndSiteIsNull(Long id, boolean b);
}
