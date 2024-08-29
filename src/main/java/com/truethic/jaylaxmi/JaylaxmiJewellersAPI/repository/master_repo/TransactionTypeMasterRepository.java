package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.TransactionTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeMasterRepository extends JpaRepository<TransactionTypeMaster, Long> {

    TransactionTypeMaster findByTransactionNameIgnoreCase(String tranxType);

    TransactionTypeMaster findByTransactionCodeIgnoreCase(String purchase_return);
}

