package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.receipt_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.receipt.TranxReceiptMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TranxReceiptMasterRepository extends JpaRepository<TranxReceiptMaster,Long> {

    @Query(
            value = " SELECT COUNT(*) FROM tranx_receipt_master_tbl WHERE company_id=?1 And " +
                    "site_id IS NULL", nativeQuery = true
    )
    Long findLastRecord(Long id);

    @Query(
            value = " SELECT COUNT(*) FROM tranx_receipt_master_tbl WHERE company_id=?1 AND site_id=?2", nativeQuery = true
    )
    Long findSiteLastRecord(Long id, Long id1);

    List<TranxReceiptMaster> findByCompanyIdAndSiteIdAndStatusOrderByIdDesc(Long id, Long id1, boolean b);

    TranxReceiptMaster findByIdAndCompanyIdAndStatus(Long receiptId, Long id, boolean b);

    TranxReceiptMaster findByIdAndStatus(long receiptId, boolean b);

    List<TranxReceiptMaster> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);
}
