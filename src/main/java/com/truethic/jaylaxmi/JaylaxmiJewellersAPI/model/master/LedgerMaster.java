package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.*;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.ledger_details.LedgerTransactionPostings;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.contra.TranxContra;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.contra.TranxContraDetails;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.gstinput.GstInputDutiesTaxes;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.gstinput.GstInputMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.gstouput.GstOutputDutiesTaxes;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.gstouput.GstOutputMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.journal.TranxJournalDetails;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.payment.TranxPaymentPerticulars;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.payment.TranxPaymentPerticularsDetails;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.receipt.TranxReceiptPerticulars;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.tranx.receipt.TranxReceiptPerticularsDetails;
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
@Table(name = "ledger_master_tbl")
public class LedgerMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ledgerName;
    private String ledgerCode;
    private String uniqueCode;
    private String mailingName;
    private String openingBalType;
    private Double openingBal;
    private String address;
    private Long pincode;
    private String email;
    private Long mobile;
    private Boolean taxable;
    private String gstin;
    private String stateCode;
    private Long registrationType;
    private LocalDate dateOfRegistration;
    private String pancard;
    private String bankName;
    private String accountNumber;
    private String ifsc;
    private String bankBranch;
    private String taxType;
    private String slugName;
    private Long createdBy;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private Long updatedBy;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Boolean status;
    private String underPrefix;
    private Boolean isDeleted;
    private Boolean isDefaultLedger;
    private Boolean isPrivate;
    /* pune visit new changes */
    private Integer creditDays;
    private String applicableFrom; //from billDate or deliveryDate
    private String foodLicenseNo;
    private LocalDate fssaiExpiry; //Food License Expiry Date
    private Boolean tds;
    private LocalDate tdsApplicableDate;
    private Boolean tcs;
    private LocalDate tcsApplicableDate;
    private String district;
    private String area;
    private String landMark;
    private String city;
    private String drugLicenseNo;
    private LocalDate drugExpiry;
    private Double salesRate;
    /* ..... End .... */

    @ManyToOne
    @JoinColumn(name = "principle_id")
    @JsonManagedReference
    private Principles principles;

    @ManyToOne
    @JoinColumn(name = "principle_groups_id")
    @JsonManagedReference
    private PrincipleGroups principleGroups;

    @ManyToOne
    @JoinColumn(name = "foundation_id")
    @JsonManagedReference
    private Foundations foundations;

    @ManyToOne
    @JoinColumn(name = "site_id")
    @JsonManagedReference
    private Site site;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonManagedReference
    private Company company;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonManagedReference
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonManagedReference
    private State state;

    @ManyToOne
    @JoinColumn(name = "balancing_method_id")
    @JsonManagedReference
    private BalancingMethod balancingMethod;

    @ManyToOne
    @JoinColumn(name = "associates_groups_id")
    @JsonManagedReference
    private AssociateGroups associateGroups;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LedgerDeptDetails> ledgerDeptDetails;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LedgerGstDetails> ledgerGstDetails;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LedgerShippingAddress> ledgerShippingAddresses;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LedgerBillingDetails> ledgerBillingDetails;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LedgerBankDetails> ledgerBankDetails;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<LedgerTransactionPostings> ledgerTransactionPostings;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxContra> tranxContras;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxContraDetails> tranxContraDetails;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxReceiptPerticulars> tranxReceiptPerticulars;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxReceiptPerticularsDetails> tranxReceiptPerticularsDetails;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxPaymentPerticulars> tranxPaymentPerticulars;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxPaymentPerticularsDetails> tranxPaymentPerticularsDetails;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<TranxJournalDetails> tranxJournalDetails;

    /**** Modification after PK visits at Solapur 25th to 30th January 2023 ******/
    private String licenseNo;
    private LocalDate licenseExpiry;
    private LocalDate foodLicenseExpiry;
    private String manufacturingLicenseNo;
    private LocalDate manufacturingLicenseExpiry;
    private LocalDate gstTransferDate;
    private String place;
    private String businessType;
    private String businessTrade;
    private String route;
    private LocalDate creditBillDate;
    private LocalDate lrBillDate;
    private LocalDate anniversary;
    private LocalDate Dob;
    private String creditTypeDays; //no.of Days,
    private String creditTypeBills; //no of Bills
    private String creditTypeValue; // Bill Value
    private Double creditNumBills;
    private Double creditBillValue;
    private Boolean isFirstDiscountPerCalculate; // if true then first disc per calculate then apply disc amount on amount in tranx level
    private Boolean takeDiscountAmountInLumpsum; // if true then take discount amount in lumpsum else disc amount per piece in tranx level
    private Boolean isMigrated; // 1: Migrated from Compositions to Registered And 0 : otherwise
            // Migrated from Unregistered to Compositions
            // Migrated from Unregistered to Registered
    private String columnA;   // columnA = salesman
    private String columnB;
    private String columnC;
    private String columnD;
    private Double columnE;
    private Double columnF;
    private Double columnG;
    private Double columnH;
    private LocalDate columnI;
    private LocalDate columnJ;
    private LocalDate columnK;
    private LocalDate columnL;
    private Long columnM;
    private Long columnN;
    private Boolean columnO;
    private Boolean columnP;
    private Boolean columnQ;
    private Boolean columnR;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<GstInputMaster> gstInputMasters;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<GstInputDutiesTaxes> gstInputDutiesTaxes;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<GstOutputMaster> gstOutputMasters;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<GstOutputDutiesTaxes> gstOutputDutiesTaxes;
       private Long areaId;
       private Long salesmanId;
    /*** END ****/
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonManagedReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    @JsonBackReference
    private Institute institute;

//    @ManyToOne
//    @JoinColumn(name = "allowance_id")
//    @JsonManagedReference
//    private Allowance allowance;

    @ManyToOne
    @JoinColumn(name = "deduction_id")
    @JsonManagedReference
    private Deduction deduction;

    @ManyToOne
    @JoinColumn(name = "allocation_id")
    @JsonManagedReference
    private Allocation allocation;

    @ManyToOne
    @JoinColumn(name = "payhead_id")
    @JsonManagedReference
    private Payhead payhead;

}
