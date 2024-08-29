package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.contra_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.contra.TranxContraMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TranxContraMasterRepository extends JpaRepository<TranxContraMaster,Long> {
    @Query(
            value = "select COUNT(*) from tranx_contra_master_tbl WHERE company_id=?1 AND site_id IS NULL", nativeQuery = true
    )
    Long findLastRecord(Long id);

    @Query(
            value = "select COUNT(*) from tranx_contra_master_tbl WHERE company_id=?1 And site_id=?2", nativeQuery = true
    )
    Long findSiteLastRecord(Long id,Long id1);
    TranxContraMaster findByIdAndStatus(long contra_id, boolean b);

    TranxContraMaster findByIdAndCompanyIdAndStatus(Long contraId, Long id, boolean b);

    List<TranxContraMaster> findByCompanyIdAndSiteIdAndStatusOrderByIdDesc(Long id, Long id1, boolean b);

    List<TranxContraMaster> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);
}
