package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;


import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionStatusRepository extends JpaRepository<TransactionStatus,Long> {
    List<TransactionStatus> findAllByStatus(boolean b);

    TransactionStatus findByStatusNameAndStatus(String opened,boolean status);
}
