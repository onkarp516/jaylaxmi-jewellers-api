package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo;


import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountryCode(String in);

    State findByStateCode(String stateCode);
}
