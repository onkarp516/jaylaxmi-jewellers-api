package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findByIdAndStatus(long documentId, boolean b);

    List<Document> findAllByStatusOrderByNameAsc(boolean b);
    List<Document> findAllByInstituteIdAndStatusOrderByNameAsc(long instituteId, boolean b);

    Page<Document> findByStatusOrderByIdDesc(Pageable pageable, boolean b);
}
