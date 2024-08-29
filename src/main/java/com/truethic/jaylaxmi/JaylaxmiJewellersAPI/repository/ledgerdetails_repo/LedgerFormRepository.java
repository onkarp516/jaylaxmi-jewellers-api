package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerFormParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerFormRepository extends JpaRepository<LedgerFormParameter,Long> {
    LedgerFormParameter findByFormName(String others);
}
