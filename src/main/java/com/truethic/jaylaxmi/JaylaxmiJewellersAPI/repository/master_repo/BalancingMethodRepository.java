package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.BalancingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalancingMethodRepository extends JpaRepository<BalancingMethod,Long> {
    BalancingMethod findByIdAndStatus(long balancing_method, boolean b);
}
