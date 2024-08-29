package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.Foundations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoundationRepository extends JpaRepository<Foundations,Long> {

    Foundations findByIdAndStatus(Long foundationId, boolean b);
}
