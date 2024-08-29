package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;


import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.PrincipleGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrincipleGroupsRepository extends JpaRepository<PrincipleGroups, Long> {
    PrincipleGroups findByIdAndStatus(long principle_group_id, boolean b);

    PrincipleGroups findByGroupNameIgnoreCase(String s);

    List<PrincipleGroups> findAllByStatus(boolean b);
    

}
