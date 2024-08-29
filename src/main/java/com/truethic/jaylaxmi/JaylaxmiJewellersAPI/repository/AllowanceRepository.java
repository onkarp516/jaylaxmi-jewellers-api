package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AllowanceRepository extends JpaRepository<Allowance, Long> {
    Allowance findByIdAndStatus(Long id, boolean b);

    @Query(value = "SELECT * FROM `allowance_tbl` WHERE status=1 and allowance_status=1", nativeQuery = true)
    List<Allowance> findAllBystatus();

    @Query(value = "SELECT IFNULL(SUM(amount),0) FROM `allowance_tbl` WHERE status=1", nativeQuery = true)
    double getSumOfAllowance();
}
