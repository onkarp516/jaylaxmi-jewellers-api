package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerDeptDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerDeptDetailsRepository extends JpaRepository<LedgerDeptDetails,Long> {
    List<LedgerDeptDetails> findByLedgerMasterIdAndStatus(Long ledgerId, boolean b);

    LedgerDeptDetails findByIdAndStatus(long id, boolean b);
}
