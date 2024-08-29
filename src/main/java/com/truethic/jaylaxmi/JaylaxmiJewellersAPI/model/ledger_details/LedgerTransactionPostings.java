package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.ledger_details;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Company;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Site;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.AssociateGroups;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.FiscalYear;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.TransactionTypeMaster;
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
@Table(name = "ledger_transaction_postings_tbl")
public class LedgerTransactionPostings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ledger_master_id")
    @JsonManagedReference
    private LedgerMaster ledgerMaster;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    @JsonManagedReference
    private TransactionTypeMaster transactionType;

    @ManyToOne
    @JoinColumn(name = "associate_groups_id")
    @JsonManagedReference
    private AssociateGroups associateGroups;

    @ManyToOne
    @JoinColumn(name = "fiscal_year_id")
    @JsonManagedReference
    private FiscalYear fiscalYear;

    @ManyToOne
    @JoinColumn(name = "site_id")
    @JsonManagedReference
    private Site site;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonManagedReference
    private Company company;

    private Double amount;
    private LocalDate transactionDate;
    private Long transactionId; // transaction id
    private String invoiceNo; //Invoice Number
    private String ledgerType;//CR or DR
    private String tranxType; //Purchase,Sales,Payment,Receipt,Purchase Return,Sales Return etc;
    private String operations;//insert,update,delete
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long createdBy;
    private Long updatedBy;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Boolean status;
}
