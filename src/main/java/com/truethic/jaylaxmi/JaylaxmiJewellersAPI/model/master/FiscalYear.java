package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.ledger_details.LedgerTransactionPostings;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.contra.TranxContraMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.gstinput.GstInputMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.gstouput.GstOutputMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.journal.TranxJournalMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.payment.TranxPaymentMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime; 
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fiscal_year_tbl")
public class FiscalYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int monthStart;
    private int monthEnd;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String fiscalYear;
    private LocalDate fiscalYearEndDate;
    private String abbreviation;
    private Long createdBy;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long updatedBy;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Boolean status;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LedgerTransactionPostings> ledgerTransactionPostings;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxContraMaster> tranxContraMasters;

//    @JsonBackReference
//    @OneToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<TranxReceiptMaster> tranxReceiptMasters;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxPaymentMaster> tranxPaymentMasters;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxJournalMaster> tranxJournalMasters;

//    @JsonBackReference
//    @OneToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<InventoryDetailsPostings> inventoryDetailsPostings;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<GstInputMaster> gstInputMasters;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<GstOutputMaster> gstOutputMasters;

}
