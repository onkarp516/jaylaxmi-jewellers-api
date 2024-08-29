package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.journal_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.journal.TranxJournalMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TranxJournalMasterRepository extends JpaRepository<TranxJournalMaster,Long> {
    @Query(
            value = "select COUNT(*) from tranx_journal_master_tbl WHERE company_id=?1 AND site_id IS NULL", nativeQuery = true
    )
    Long findLastRecord(Long id);


    @Query(
            value = " SELECT COUNT(*) FROM tranx_journal_master_tbl WHERE company_id=?1 AND site_id=?2", nativeQuery = true
    )
    Long findSiteLastRecord(Long id, Long id1);
    List<TranxJournalMaster> findByCompanyIdAndSiteIdAndStatusOrderByIdDesc(Long id, Long id1, boolean b);

    TranxJournalMaster findByIdAndStatus(long journal_id, boolean b);

    TranxJournalMaster findByIdAndCompanyIdAndStatus(Long journalId, Long id, boolean b);

    List<TranxJournalMaster> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);
}
