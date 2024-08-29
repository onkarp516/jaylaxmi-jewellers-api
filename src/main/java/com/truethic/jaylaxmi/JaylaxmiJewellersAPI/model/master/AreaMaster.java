package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Company;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Site;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "area_master_tbl")

public class AreaMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaName;
    private String pincode;
    private String areaCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long createdBy;
    private Boolean status;

    @ManyToOne
//    @JsonIgnoreProperties(value = {"employee","hibernateLazyInitializer"})
    @JsonManagedReference
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "company_id")
    private Company company;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
