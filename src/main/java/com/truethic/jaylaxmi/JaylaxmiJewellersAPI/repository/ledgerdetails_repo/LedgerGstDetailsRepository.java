package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerGstDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerGstDetailsRepository extends JpaRepository<LedgerGstDetails,Long> {
    List<LedgerGstDetails> findByLedgerMasterIdAndStatus(Long ledgerId, boolean b);

    LedgerGstDetails findByIdAndStatus(long id, boolean b);
}
