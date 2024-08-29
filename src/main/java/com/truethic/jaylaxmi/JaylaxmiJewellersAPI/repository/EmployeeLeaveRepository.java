package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {
    List<EmployeeLeave> findByEmployeeIdAndStatus(Long id, boolean b);

    EmployeeLeave findByIdAndStatus(Long leaveId, boolean b);

    EmployeeLeave findByEmployeeIdAndFromDateLessThanAndToDateGreaterThan(Long id, LocalDate localDate, LocalDate localDate1);

    EmployeeLeave findByEmployeeIdAndFromDateLessThanEqualAndToDateGreaterThanEqual(Long id, LocalDate localDate, LocalDate localDate1);

    List<EmployeeLeave> findByEmployeeIdAndStatusOrderByIdDesc(Long id, boolean b);

    EmployeeLeave findByEmployeeIdAndFromDateLessThanEqualAndToDateGreaterThanEqualAndLeaveStatus(Long id, LocalDate localDate, LocalDate localDate1, String approved);

    @Query(value = "SELECT * FROM `employee_leave_tbl` WHERE ?1 BETWEEN from_date AND to_date AND leave_status='Approved'", nativeQuery = true)
    List<EmployeeLeave> getEmployeesOnLeave(String now);

    @Query(value = "SELECT * FROM `employee_leave_tbl` WHERE leave_status='Pending' AND employee_id IN(SELECT id from employee_tbl WHERE status = 1)", nativeQuery = true)
    List<EmployeeLeave> getPendingLeaveRequests();
}
