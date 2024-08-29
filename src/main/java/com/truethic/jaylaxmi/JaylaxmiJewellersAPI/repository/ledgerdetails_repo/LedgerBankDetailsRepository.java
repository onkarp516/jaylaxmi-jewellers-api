package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerBankDetailsRepository extends JpaRepository<LedgerBankDetails,Long> {
    List<LedgerBankDetails> findByLedgerMasterIdAndStatus(Long id, boolean b);

    LedgerBankDetails findByIdAndStatus(long id, boolean b);
}
