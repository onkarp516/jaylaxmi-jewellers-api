package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Downtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DowntimeRepository extends JpaRepository<Downtime, Long> {
    List<Downtime> findByStatus(boolean b);
}
