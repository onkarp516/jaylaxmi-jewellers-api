package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.ToolManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolMgmtRepository extends JpaRepository<ToolManagement, Long> {

    Page<ToolManagement> findByStatusOrderByIdDesc(Pageable pageable, boolean b);


    ToolManagement findByIdAndStatus(long id, boolean b);

    List<ToolManagement> findAllByStatus(boolean b);
}
