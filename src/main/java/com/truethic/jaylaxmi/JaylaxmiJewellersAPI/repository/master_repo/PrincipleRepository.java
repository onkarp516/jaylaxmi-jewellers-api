package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.Principles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrincipleRepository extends JpaRepository<Principles,Long> {



    List<Principles> findAllByStatus(boolean b);

    Principles findByPrincipleNameIgnoreCaseAndStatus(String key, boolean b);

    Principles findByIdAndStatus(Long ledgeIdpc, boolean b);

}
