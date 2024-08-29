package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "allocation_tbl")
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double amount;
    private Boolean status;
    private Boolean isAllowance;
    private LocalDate allocationDate;
    private Long createdBy;
    @CreationTimestamp
    private LocalDate createdAt;
    private Long updatedBy;
    private LocalDate updatedAt;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
//    @ManyToOne
//    @JsonManagedReference
//    @JoinColumn(name = "allowance_id", nullable = true)
//    private Allowance allowance;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "payhead_id", nullable = true)
    private Payhead payhead;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "deduction_id", nullable = true)
    private Deduction deduction;
    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    @JsonBackReference
    private Institute institute;

}
