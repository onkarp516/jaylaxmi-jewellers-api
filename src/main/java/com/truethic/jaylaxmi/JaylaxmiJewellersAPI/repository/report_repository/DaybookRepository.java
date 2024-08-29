package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.report_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.report.DayBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DaybookRepository extends JpaRepository<DayBook,Long> {

    @Query(
            value = "SELECT * FROM `day_book_tbl` WHERE tranx_date BETWEEN ?1 AND ?2 " +
                    "AND status=?3 AND company_id=?4 AND branch_id=?5",nativeQuery = true
    )
    List<DayBook> findByTranxDateAndStatusAndCompanyIdAndSiteId(LocalDate startDate,LocalDate endDate, boolean b, Long id, Long id1);

    @Query(
            value = "SELECT * FROM `day_book_tbl` WHERE tranx_date BETWEEN ?1 AND ?2 " +
                    "AND status=?3 AND company_id=?4",nativeQuery = true
    )
    List<DayBook>   findByTranxDateAndStatusAndCompanyId(LocalDate startDate,LocalDate endDate, boolean b, Long id);
}
