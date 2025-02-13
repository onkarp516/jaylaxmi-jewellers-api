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
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salesman_master_tbl")
public class SalesManMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String mobileNumber;
    private String pincode;
    private String address;
    private LocalDate dob;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long createdBy;
    private Boolean status;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Long updatedBy;

    @ManyToOne
    @JoinColumn(name = "site_id")
    @JsonManagedReference
    private Site site;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonManagedReference
    private Company company;

}
