package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.MasterPayhead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterPayheadRepository extends JpaRepository<MasterPayhead, Long> {
    List<MasterPayhead> findByStatus(boolean b);

    MasterPayhead findByIdAndStatus(Long id, boolean b);
}
