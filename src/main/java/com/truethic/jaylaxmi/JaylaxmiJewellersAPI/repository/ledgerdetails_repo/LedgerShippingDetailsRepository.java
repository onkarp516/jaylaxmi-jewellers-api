package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerShippingDetailsRepository extends JpaRepository<LedgerShippingAddress,Long> {
    List<LedgerShippingAddress> findByLedgerMasterIdAndStatus(Long ledgerId, boolean b);

    LedgerShippingAddress findByIdAndStatus(Long id, boolean b);
}
