package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.payment_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.payment.TranxPaymentPerticularsDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranxPaymentPerticularsDetailsRepository extends JpaRepository<TranxPaymentPerticularsDetails,Long> {
    List<TranxPaymentPerticularsDetails> findByIdAndStatus(Long id, boolean b);

    TranxPaymentPerticularsDetails findByIdAndCompanyIdAndSiteIdAndStatus(Long tranx_type, Long id, Long id1, boolean b);


    TranxPaymentPerticularsDetails findByIdAndCompanyIdAndStatus(Long tranx_type, Long id, boolean b);

    TranxPaymentPerticularsDetails findByTranxPaymentMasterIdAndStatus(Long id, boolean b);
}
