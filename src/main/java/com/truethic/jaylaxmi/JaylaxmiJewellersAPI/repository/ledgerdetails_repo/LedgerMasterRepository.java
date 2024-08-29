package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LedgerMasterRepository extends JpaRepository<LedgerMaster, Long> {

    /* Get Sundry Creditors by outlet id */
    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code, sales_rate, is_first_discount_per_calculate," +
                    " take_discount_amount_in_lumpsum FROM ledger_master_tbl WHERE company_id =?1 AND " +
                    "principle_groups_id =5 AND status=1 AND site_id IS NULL",
            nativeQuery = true
    )
    List<Object[]> findSundryCreditorsByCompanyId(Long outletId);

    /*Get Sundry Creditors by CompanyId and SiteId*/
    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code, sales_rate, is_first_discount_per_calculate," +
                    " take_discount_amount_in_lumpsum FROM ledger_master_tbl WHERE company_id=?1 AND site_id=?2 AND" +
                    " principle_groups_id=5 AND status=1",
            nativeQuery = true
    )
    List<Object[]> findSundryCreditorsByCompanyIdAndSiteId(Long outletId, Long branchId);


    /* Get Sundry Debtors by outlet id */
    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code, sales_rate, fssai_expiry, drug_expiry FROM ledger_master_tbl " +
                    "WHERE company_id =?1 AND " +
                    "principle_groups_id =1 AND status=1 AND site_id IS NULL",
            nativeQuery = true
    )
    List<Object[]> findSundryDebtorsByCompanyId(Long outletId);

    /* Get Sundry Debtors by outlet id  and Brnach Id*/
    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code, sales_rate FROM ledger_master_tbl " +
                    "WHERE company_id =?1 AND " + "site_id=?2" +
                    " AND principle_groups_id =1 AND status=1",
            nativeQuery = true
    )
    List<Object[]> findSundryDebtorsByCompanyIdAndSiteId(Long outletId, Long branchId);

    /* Get Cash-In Hand  by outlet id */
    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code FROM ledger_master_tbl WHERE company_id =?1 AND " +
                    "principle_groups_id =3 And status=1 And site_id IS NULL",
            nativeQuery = true
    )
    List<Object[]> findCashInHandByCompanyId(Long outletId);

    /* Get Cash-In Hand  by outlet id and branch id */
    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code FROM ledger_master_tbl WHERE company_id =?1 AND " +
                    "principle_groups_id =3 And status=1 And site_id=?2",
            nativeQuery = true
    )
    List<Object[]> findCashInHandByCompanyIdAndSite(Long outletId, Long branchId);

    /* Get Bank Accounts by outlet id */
    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code FROM ledger_master_tbl WHERE company_id =?1 AND " +
                    "principle_groups_id =2 And status=1 AND site_id IS NULL",
            nativeQuery = true
    )
    List<Object[]> findBankAccountsByCompanyId(Long outletId);

    @Query(
            value = "SELECT id,ledger_name,ledger_code,state_code FROM ledger_master_tbl WHERE company_id =?1 AND " +
                    "principle_groups_id =2 And status=1 AND site_id=?2",
            nativeQuery = true
    )
    List<Object[]> findBankAccountsByCompanyIdAndSite(Long outletId, Long site_id);


    List<LedgerMaster> findByCompanyIdAndPrinciplesIdAndStatus(Long outletId, Long id, boolean b);

    LedgerMaster findByIdAndStatus(Long id, boolean b);
    LedgerMaster findByIdAndInstituteIdAndStatus(Long id, Long institute_id, boolean b);

    LedgerMaster findByCompanyIdAndLedgerNameIgnoreCase(Long id, String round_off);

    LedgerMaster findByIdAndCompanyIdAndStatus(long purchase_id, Long id, boolean b);

    LedgerMaster findByLedgerNameIgnoreCaseAndCompanyId(String round_off, Long id);

    @Query(
            value = "SELECT * FROM `ledger_master_tbl` WHERE principle_groups_id =3 And status=1 And company_id=?1 " +
                    "And site_id IS NULL", nativeQuery = true
    )
    LedgerMaster findLedgerIdAndName(Long company_id);

    @Query(
            value = "SELECT * FROM `ledger_master_tbl` WHERE principle_groups_id =3 And status=1 And company_id=?1 " +
                    "And site_id=?2", nativeQuery = true
    )
    LedgerMaster findLedgerIdAndSiteIdAndName(Long company_id, Long site_id);


//    List<LedgerMaster> findByCompanyIdAndPrincipleGroupsId(Long id, Long i);

    //List<LedgerMaster> findByCompanyIdAndPrinciplesId(Long id, long l);


    @Query(
            value = "SELECT * FROM `ledger_master_tbl` WHERE (principle_groups_id =3 Or principle_groups_id=2) And " +
                    "status=1 And company_id=?1 And site_id IS NULL", nativeQuery = true
    )
    List<LedgerMaster> findBankAccountCashAccount(Long id);


    @Query(
            value = "SELECT * FROM `ledger_master_tbl` WHERE (principle_groups_id =3 Or principle_groups_id=2)  And " +
                    "status=1 And company_id=?1 AND site_id=?2", nativeQuery = true
    )
    List<LedgerMaster> findSiteBankAccountCashAccount(Long id, Long branchId);

    @Query(
            value = "SELECT * FROM `ledger_master_tbl` WHERE  (principle_groups_id  NOT IN (3,2) OR " +
                    "principle_groups_id IS NULL) And status=1 And company_id=?1 And site_id IS NULL", nativeQuery = true
    )
    List<LedgerMaster> findledgers(Long id);

    @Query(
            value = "SELECT * FROM `ledger_master_tbl` WHERE  (principle_groups_id  NOT IN (3,2) OR principle_groups_id IS NULL) And status=1 And company_id=?1 And site_id=?2", nativeQuery = true
    )
    List<LedgerMaster> findledgersBySite(Long id, Long id1);

    List<LedgerMaster> findByCompanyIdAndSiteIdAndPrinciplesIdAndStatus(Long outletId, Long branchId, Long id, boolean b);


    LedgerMaster findByUniqueCodeAndCompanyIdAndSiteIdAndStatus(String caih, Long id, Long id1, boolean b);

    LedgerMaster findByUniqueCodeAndCompanyIdAndStatus(String caih, Long id, boolean b);

    LedgerMaster findByMobileAndStatus(Long mobileNo, boolean b);

    LedgerMaster findByLedgerNameIgnoreCaseAndCompanyIdAndSiteIdAndStatus(String counter_customer, Long id, Long id1, boolean b);

    @Query(
            value = "SELECT ledger_master_tbl.opening_bal FROM ledger_master_tbl WHERE company_id=?1 AND site_id=?2 AND status=?3 AND" +
                    " id=?4 ", nativeQuery = true
    )
    Double findByIdAndCompanyIdAndSiteIdAndStatus(Long id, Long id1, boolean b, Long principle_id, LocalDate startMonthDate);

    @Query(
            value = "SELECT ledger_master_tbl.opening_bal FROM ledger_master_tbl WHERE company_id=?1 AND site_id=?2 AND status=?3 AND" +
                    " id=?4 ", nativeQuery = true
    )
    Double findByIdAndCompanyIdAndSiteIdAndStatuslm(Long id, Long id1, boolean b, Long principle_id);

    @Query(
            value = "SELECT ledger_master_tbl.opening_bal FROM ledger_master_tbl WHERE company_id=?1 AND status=?2 AND" +
                    " id=?3 ", nativeQuery = true
    )
    Double findByIdAndCompanyIdAndStatuslm(Long id, boolean b, Long principle_id);

    @Query(
            value = " SELECT * FROM ledger_master_tbl WHERE company_id=?1 AND (site_id=?2 OR site_id IS NULL)" +
                    "AND principle_id=?3 AND principle_groups_id=?4 " +
                    "AND lower(ledger_name=?5) AND status=?6", nativeQuery = true
    )
    LedgerMaster findDuplicateWithName(Long id, Long branchId, Long principleId, Long subPrincipleId, String ledger_name, boolean b);

    @Query(
            value = " SELECT * FROM ledger_master_tbl WHERE company_id=?1 AND (site_id=?2 OR site_id IS NULL)" +
                    "AND principle_id=?3 AND principle_groups_id=?4 " +
                    "AND lower(ledger_code=?5) AND status=?6", nativeQuery = true
    )
    LedgerMaster findDuplicateWithCode(Long id, Long branchId, long principle_id, Long pgroupId, String ledger_code, boolean b);

    @Query(
            value = " SELECT * FROM ledger_master_tbl WHERE company_id=?1 AND (site_id=?2 OR site_id IS NULL)" +
                    "AND principle_id=?3 AND lower(ledger_name=?4) AND status=?5", nativeQuery = true
    )
    LedgerMaster findDuplicate(Long id, Long branchId, long principle_id, String ledger_name, boolean b);


    @Query(
            value = "SELECT ledger_master_tbl.opening_bal FROM ledger_master_tbl WHERE ledger_master_tbl.id=?1",
            nativeQuery = true
    )
    Double findOpeningBalance(Long ledgerId);


    LedgerMaster findByIdAndIsDefaultLedgerAndStatus(long id, boolean b, boolean b1);


    List<LedgerMaster> findByCompanyIdAndSiteIdAndStatusOrderByIdDesc(Long id, Long id1, boolean b);


    List<LedgerMaster> findByCompanyIdAndSiteIdAndPrincipleGroupsIdAndStatus(Long id, Long id1, long l, boolean b);

    List<LedgerMaster> findByCompanyIdAndStatusAndSiteIsNullOrderByIdDesc(Long id, boolean b);

    List<LedgerMaster> findByCompanyIdAndPrinciplesIdAndStatusAndSiteIsNull(Long outletId, Long id, boolean b);


    LedgerMaster findByLedgerNameIgnoreCaseAndCompanyIdAndStatusAndSiteIsNull(String counter_customer, Long id, boolean b);

    List<LedgerMaster> findByCompanyIdAndPrincipleGroupsIdAndStatusAndSiteIsNull(Long id, long l, boolean b);

    @Query(
            value = " SELECT IFNULL(SUM(opening_bal),0.0) FROM `ledger_master_tbl` WHERE id=?1 AND " +
                    "AND company_id=?2 AND site_id=?3 AND opening_bal_type=?4 ", nativeQuery = true
    )
    Double findLedgerOpeningStocksSite(Long productId, Long outletId, Long branchId, String openingType);

    @Query(
            value = " SELECT IFNULL(SUM(opening_bal),0.0) FROM `ledger_master_tbl` WHERE id=?1 AND " +
                    "AND company_id=?2 AND site_id IS NULL AND opening_bal_type=?3 ", nativeQuery = true
    )
    Double findLedgerOpeningStocks(Long productId, Long outletId, Long branchId, String openingType);

    LedgerMaster findByCompanyIdAndSiteIdAndLedgerNameIgnoreCase(Long id, Long id1, String round_off);

    LedgerMaster findByCompanyIdAndLedgerNameIgnoreCaseAndSiteIsNull(Long id, String round_off);


    LedgerMaster findByUniqueCodeAndCompanyIdAndStatusAndSiteIsNull(String caih, Long id, boolean b);

    @Query(
            value = " SELECT * FROM `ledger_master_tbl` WHERE company_id=?1 AND site_id=?2 " +
                    "AND (principle_groups_id=?3 OR principle_groups_id=?4) AND status=?5 ", nativeQuery = true
    )
    List<LedgerMaster> findBySCSDWithSite(Long outletId, Long branchId, long pg1, long pg2, boolean b);

    @Query(
            value = " SELECT * FROM `ledger_master_tbl` WHERE company_id=?1 AND site_id IS NULL " +
                    "AND (principle_groups_id=?2 OR principle_groups_id=?3) AND status=?4 ", nativeQuery = true
    )
    List<LedgerMaster> findBySCSD(Long outletId, long pg1, long pg2, boolean b);

    LedgerMaster findByCompanyIdAndSiteIdAndStatusAndId(Long id, Long id1, boolean b, Long ledgerId);

    LedgerMaster findByCompanyIdAndStatusAndIdAndSiteIsNull(Long id, boolean b, Long ledgerId);

    @Query(
            value = " SELECT * FROM `ledger_master_tbl` WHERE company_id=?1 AND site_id=?2" +
                    " AND (ledger_code LIKE ?3 OR ledger_name LIKE ?3 OR city LIKE ?3 OR mobile LIKE ?3)" +
                    " AND (principle_groups_id=?4 OR principle_groups_id=?5) AND status=?6", nativeQuery = true
    )
    List<LedgerMaster> findSearchKeyWithSite(Long outletId, Long branchId, String searchKey, long l, long l1, boolean b);

    @Query(
            value = "SELECT * FROM `ledger_master_tbl` WHERE company_id=?1 AND site_id IS NULL" +
                    " AND (ledger_code LIKE %?2% OR ledger_name LIKE %?2% OR city LIKE %?2% OR mobile LIKE %?2%)" +
                    " AND (principle_groups_id=?4 OR principle_groups_id=?5) AND status=?6", nativeQuery = true
    )
    List<LedgerMaster> findSearchKey(Long outletId, String searchKey, long l, long l1, boolean b);

    List<LedgerMaster> findByCompanyIdAndSiteIdAndStatusOrderByIdAsc(Long id, Long id1, boolean b);

    List<LedgerMaster> findByCompanyIdAndStatusAndSiteIsNullOrderByIdAsc(Long id, boolean b);

    List<LedgerMaster> findByUniqueCodeAndSiteIdAndCompanyIdAndStatus(String baac, Long id, Long id1, boolean b);

    List<LedgerMaster> findByUniqueCodeAndSiteIsNullAndCompanyIdAndStatus(String baac, Long id, boolean b);

    List<LedgerMaster> findByCompanyIdAndSiteIdAndInstituteIdAndStatus(Long id, Long id1, Long id2, boolean b);

    LedgerMaster findByEmployeeIdAndStatus(Long id, boolean b);
}

