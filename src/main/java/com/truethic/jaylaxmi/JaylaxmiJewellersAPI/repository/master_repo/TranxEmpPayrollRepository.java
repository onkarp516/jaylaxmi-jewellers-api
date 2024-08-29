package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.TranxEmpPayroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TranxEmpPayrollRepository extends JpaRepository<TranxEmpPayroll, Long> {

    @Query(value = "SELECT * FROM `tranx_emp_payroll_tbl` WHERE employee_id=?1 AND MONTH(salary_month)=?2 AND status=1", nativeQuery = true)
    TranxEmpPayroll checkIfSalaryProcessed(Long id, String month);
}
