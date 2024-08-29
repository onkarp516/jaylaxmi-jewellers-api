package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;


import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByStatus(boolean b);
    List<Company> findAllByInstituteIdAndStatus(long instituteId, boolean b);

    Company findByIdAndStatus(long id, boolean b);
}
