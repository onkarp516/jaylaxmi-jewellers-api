package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.tranx_repository.gstinput_repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.gstinput.GstInputMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GstInputMasterRepository extends JpaRepository<GstInputMaster, Long> {

    @Query(
            value = "select COUNT(*) from tranx_gst_input_tbl WHERE company_id=?1 And site_id=?2", nativeQuery = true
    )
    Long findSiteLastRecord(Long id, Long id1);

    @Query(
            value = "select COUNT(*) from tranx_gst_input_tbl WHERE company_id=?1 AND site_id IS NULL", nativeQuery = true
    )
    Long findLastRecord(Long id);

    List<GstInputMaster> findByCompanyIdAndSiteIdAndStatusOrderByIdDesc(Long id, Long id1, boolean b);

    List<GstInputMaster> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);

    GstInputMaster findByIdAndStatus(Long gstInputId, boolean b);
}
