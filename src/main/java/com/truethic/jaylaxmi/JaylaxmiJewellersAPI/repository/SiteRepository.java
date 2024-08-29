package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findByIdAndStatus(long id, boolean b);

    List<Site> findByStatus(boolean b);
    List<Site> findByInstituteIdAndStatus(long instituteId, boolean b);
}
