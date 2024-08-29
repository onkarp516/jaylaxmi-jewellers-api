package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Allowance;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Payhead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayheadRepository extends JpaRepository<Payhead, Long> {
    Payhead findByIdAndStatus(Long payheadId, boolean b);

    List<Payhead> findByStatus(boolean b);

    List<Payhead> findByIsAdminRecordAndStatusOrderByPercentageOfId(boolean b, boolean b1);

    @Query(value = "SELECT * FROM `payhead_tbl` WHERE status=1 and payhead_status=1 AND is_admin_record=0", nativeQuery = true)
    List<Payhead> findAllBystatus();

    List<Payhead> findByPayheadStatus(boolean b);
}
