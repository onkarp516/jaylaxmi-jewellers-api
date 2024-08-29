package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.EmployeeDocument;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Long> {
    EmployeeDocument findByIdAndStatus(long empDocumentId, boolean b);

    @Modifying
    @Transactional
    @Cascade(CascadeType.DELETE)
    @Query(value = "DELETE FROM employee_document_tbl WHERE id=?1", nativeQuery = true)
    void deleteDocumentFromEmployee(Long id);
}
