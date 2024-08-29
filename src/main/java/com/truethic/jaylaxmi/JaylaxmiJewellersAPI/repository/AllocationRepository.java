package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    Allocation findByIdAndStatus(Long id, boolean b);

    @Query(value = "SELECT IFNULL(SUM(amount),0) FROM `allocation_tbl` WHERE employee_id=?1 AND is_allowance=?2 AND status=?3 AND YEAR(allocation_date)='?4' AND MONTH(allocation_date)='?5'", nativeQuery = true)
    double getSumOfAllocations(Long employeeId, boolean isAllowance, boolean b, int year, int monthValue);


    @Query(value = "SELECT * FROM `allocation_tbl` WHERE employee_id=?1 AND allocation_date BETWEEN ?2 AND ?3 AND status=1;", nativeQuery = true)
    List<Allocation> getEmployeeAllocations(Long employeeId, String startDate, String endDate);

    @Query(value = "SELECT * FROM `allocation_tbl` WHERE employee_id=?1 AND status=?2;", nativeQuery = true)
    List<Allocation> findByEmployeeIdAndStatus(Long empId, boolean b);
}
