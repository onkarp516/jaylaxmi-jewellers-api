package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;


import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByName(String india);
}
