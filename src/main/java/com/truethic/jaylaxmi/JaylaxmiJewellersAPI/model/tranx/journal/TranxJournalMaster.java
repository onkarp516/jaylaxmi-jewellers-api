package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.journal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Company;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Site;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.FiscalYear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tranx_journal_master_tbl")


public class TranxJournalMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "site_id")
    @JsonManagedReference
    private Site site;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonManagedReference
    private Company company;

    @ManyToOne
    @JoinColumn(name = "fiscal_year_id")
    @JsonManagedReference
    private FiscalYear fiscalYear;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TranxJournalDetails> tranxJournalDetails;


    private String journalNo;
    private double journalSrNo;
    private LocalDate transcationDate;
    private double totalAmt;
    private boolean status;
    private String narrations;
    private String financialYear;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long createdBy;
}
