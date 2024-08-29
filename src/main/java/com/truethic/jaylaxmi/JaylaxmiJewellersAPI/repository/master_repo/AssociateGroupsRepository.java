package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.AssociateGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssociateGroupsRepository extends JpaRepository<AssociateGroups, Long> {
    AssociateGroups findByIdAndStatus(long associates_id, boolean b);

    @Query(
            value = " SELECT associates_name FROM `associates_groups_tbl` WHERE id=?1 AND Status=?2",
            nativeQuery = true
    )
    String findName(Long associateId, boolean b);

    List<AssociateGroups> findByCompanyId(Long id);


    @Query(
            value = " SELECT * FROM associates_groups_tbl WHERE company_id=?1 AND (site_id=?2 OR site_id IS NULL) " +
                    "AND principle_id=?3 AND (principle_groups_id=?4 OR principle_groups_id IS NULL) " +
                    "AND associates_name=?5 AND status=?6", nativeQuery = true
    )
    AssociateGroups findDuplicateAG(Long outletId, Long branchId, Long principleId, Long pgroupId,
                                    String associates_name, Boolean status);

    List<AssociateGroups> findByCompanyIdAndStatusAndSiteIdOrderByIdDesc(Long id, boolean b, Long id1);

    List<AssociateGroups> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);
    List<AssociateGroups> findByCompanyIdAndStatus(Long id, boolean b);
//    List<AssociateGroups> findByCompanyIdAndStatusAndSiteIdOrderByIdDesc(Long id, boolean b, Long id1);
//
//    List<AssociateGroups> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);
}
