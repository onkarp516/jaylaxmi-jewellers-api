package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.payment_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.payment.TranxPaymentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TranxPaymentMasterRepository extends JpaRepository<TranxPaymentMaster,Long>{
    @Query(
            value = " SELECT COUNT(*) FROM tranx_payment_master_tbl WHERE company_id=?1 AND site_id IS NULL", nativeQuery = true
    )
    Long findLastRecord(Long id);

    List<TranxPaymentMaster> findByCompanyIdAndSiteIdAndStatusOrderByIdDesc(Long id, Long id1, boolean b);

    @Query(
            value = " SELECT COUNT(*) FROM tranx_payment_master_tbl WHERE company_id=?1 AND site_id=?2", nativeQuery = true
    )
    Long findSiteLastRecord(Long id, Long id1);

    TranxPaymentMaster findByIdAndCompanyIdAndStatus(Long paymentId, Long id, boolean b);

    TranxPaymentMaster findByIdAndStatus(long payment_id, boolean b);

    List<TranxPaymentMaster> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);
}
