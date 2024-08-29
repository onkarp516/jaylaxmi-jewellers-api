package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.payment_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.payment.TranxPaymentPerticulars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TranxPaymentPerticularsRepository extends JpaRepository<TranxPaymentPerticulars, Long> {

    List<TranxPaymentPerticulars> findByCompanyIdAndStatusOrderByIdDesc(Long id, boolean b);

    @Query(
            value = "SELECT * FROM tranx_payment_perticulars_tbl WHERE" +
                    " tranx_payment_master_id=?1 AND company_id=?2 AND status=?3 ",
            nativeQuery = true

    )
    List<TranxPaymentPerticulars> findLedgerName(Long id, Long outlteId, boolean status);

    List<TranxPaymentPerticulars> findByTranxPaymentMasterIdAndStatus(Long id, boolean b);

    TranxPaymentPerticulars findByIdAndStatus(Long detailsId, boolean b);

    TranxPaymentPerticulars findByTranxPaymentMasterIdAndCompanyIdAndSiteIdAndStatusAndType(Long transactionId, Long id, Long id1, boolean b, String dr);

    TranxPaymentPerticulars findByTranxPaymentMasterIdAndCompanyIdAndStatusAndType(Long transactionId, Long id, boolean b, String dr);

    TranxPaymentPerticulars findByTranxPaymentMasterIdAndTypeAndStatus(Long id, String dr, boolean b);
}
