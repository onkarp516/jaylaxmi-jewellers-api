package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Designation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface DesignationRepository extends JpaRepository<Designation, Long> {
    Page<Designation> findByStatusOrderByIdDesc(Pageable pageable, boolean b);

    @Procedure("insertDesignation")
    void insertDesignation(String desigName, String code);

    Designation findByIdAndStatus(Long designationId, boolean b);

    List<Designation> findAllByStatus(boolean b);
    List<Designation> findAllByInstituteIdAndStatus(long instituteId, boolean b);
}
