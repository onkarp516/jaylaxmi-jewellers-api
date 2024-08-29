package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Allowance;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.BalancingMethod;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.Foundations;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.LedgerMaster;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.master.Principles;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.PayheadRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.ledgerdetails_repo.LedgerMasterRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo.BalancingMethodRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.master_repo.PrincipleRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.response.ResponseMessage;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.dto.GenericDTData;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.dto.PayheadDTO;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Payhead;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Users;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.JwtTokenUtil;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PayheadService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PayheadRepository payheadRepository;
    @Autowired
    Utility utility;
    @Autowired
    private PrincipleRepository principleRepository;
    @Autowired
    private BalancingMethodRepository balancingMethodRepository;
    @Autowired
    private LedgerMasterRepository ledgerMasterRepository;

    public Object createPayhead(Map<String, String> requestParam, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        Payhead payhead = new Payhead();
        Users users = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
        try {
            payhead.setName(requestParam.get("payheadName"));
            payhead.setPercentage(Double.parseDouble(requestParam.get("percentage")));
            payhead.setPayheadStatus(Boolean.parseBoolean(requestParam.get("payheadStatus")));

            if (requestParam.get("payheadId") != null) {
                Long payheadId = Long.valueOf(requestParam.get("payheadId"));
                Payhead payhead1 = payheadRepository.findByIdAndStatus(payheadId, true);
                payhead.setPercentageOf(payhead1);
            }
            payhead.setPayheadSlug(utility.getKeyName(requestParam.get("payheadName"),false));
            payhead.setCreatedBy(users.getId());
            payhead.setInstitute(users.getInstitute());
            payhead.setCreatedAt(LocalDateTime.now());
            payhead.setStatus(true);
            payhead.setIsAdminRecord(false);
            try {
                Payhead object = payheadRepository.save(payhead);
                if(object != null){
//                    PrincipleGroups groups = principleGroupsRepository.findByIdAndStatus(1L, true);
                    Principles principles = principleRepository.findByIdAndStatus(11L,true);
                    Foundations foundations = principles.getFoundations();
                    BalancingMethod balancingMethod = balancingMethodRepository.findByIdAndStatus(2L, true);
                    LedgerMaster mLedgerMaster = new LedgerMaster();
                    mLedgerMaster.setUniqueCode("DIEX");
                    mLedgerMaster.setOpeningBalType("DR");
                    mLedgerMaster.setAddress("NA");
                    mLedgerMaster.setOpeningBal(0.0);
                    mLedgerMaster.setPincode(0L);
                    mLedgerMaster.setEmail("NA");
//                    mLedgerMaster.setMobile(users.getMobileNumber());
                    mLedgerMaster.setTaxable(false);
                    mLedgerMaster.setGstin("NA");
                    mLedgerMaster.setStateCode("NA");
                    mLedgerMaster.setPancard("NA");
                    mLedgerMaster.setBankName("NA");
                    mLedgerMaster.setAccountNumber("NA");
                    mLedgerMaster.setIfsc("NA");
                    mLedgerMaster.setBankBranch("NA");
                    mLedgerMaster.setCreatedBy(users.getId());
                    mLedgerMaster.setTaxType("NA");
                    mLedgerMaster.setSlugName("direct_expenses");
                    mLedgerMaster.setStatus(true);
                    mLedgerMaster.setUnderPrefix("AG#2");
                    mLedgerMaster.setIsDefaultLedger(false);
                    mLedgerMaster.setIsDeleted(true);
                    mLedgerMaster.setPrinciples(principles);
                    mLedgerMaster.setFoundations(foundations);
//                    mLedgerMaster.setPrincipleGroups(groups);
                    mLedgerMaster.setSite(users.getSite());
                    mLedgerMaster.setCompany(users.getCompany());
                    mLedgerMaster.setBalancingMethod(balancingMethod);
                    mLedgerMaster.setLedgerName(object.getName());
//                    mLedgerMaster.setMailingName(employee.getFirstName()+" "+employee.getMiddleName()+" "+employee.getLastName());
//                    mLedgerMaster.setEmployee(employee);
                    mLedgerMaster.setInstitute(users.getInstitute());
                    mLedgerMaster.setPayhead(object);
                    LedgerMaster newLedger = ledgerMasterRepository.save(mLedgerMaster);
                }
                responseMessage.setMessage("Payhead created successfully");
                responseMessage.setResponseStatus(HttpStatus.OK.value());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception " + e.getMessage());
                responseMessage.setMessage("Failed to create payhead");
                responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception " + e.getMessage());
            responseMessage.setMessage("Failed to create payhead");
            responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return responseMessage;
    }

    public Object DTPayhead(Map<String, String> request, HttpServletRequest httpServletRequest) {
        Integer from = Integer.parseInt(request.get("from"));
        Integer to = Integer.parseInt(request.get("to"));
        String searchText = request.get("searchText");
        Users user = jwtTokenUtil.getUserDataFromToken(httpServletRequest.getHeader("Authorization").substring(7));
        GenericDTData genericDTData = new GenericDTData();
        List<Payhead> payheadList = new ArrayList<>();
        List<PayheadDTO> payheadDTOList = new ArrayList<>();
        try {
            String query = "SELECT payhead_tbl.id, payhead_tbl.name, payhead_tbl.percentage, payhead_tbl.institute_id, " +
                    "payhead_tbl.amount, payhead_tbl.created_at, payhead_tbl.created_by, payhead_tbl.is_admin_record, payhead_tbl.payhead_status," +
                    "payhead_tbl.status, payhead_tbl.updated_at, payhead_tbl.updated_by, payhead_tbl.percentage_of, payhead_tbl.payhead_slug," +
                    " mst_tbl.name as parent_payhead FROM `payhead_tbl` LEFT JOIN payhead_tbl as mst_tbl ON" +
                    " mst_tbl.id=payhead_tbl.percentage_of WHERE payhead_tbl.status=1 AND payhead_tbl.is_admin_record=0 AND payhead_tbl.institute_id="+user.getInstitute().getId();

            if (!searchText.equalsIgnoreCase("")) {
                query = query + " AND (payhead_tbl.name LIKE '%" + searchText + "%' OR mst_tbl.name LIKE '%" +
                        searchText + "%' OR payhead_tbl.percentage LIKE '%" + searchText + "%')";
            }

            String jsonToStr = request.get("sort");
            JsonObject jsonObject = new Gson().fromJson(jsonToStr, JsonObject.class);

            if (!jsonObject.get("colId").toString().equalsIgnoreCase("null") &&
                    jsonObject.get("colId").toString() != null) {
                System.out.println(" ORDER BY " + jsonObject.get("colId").toString());
                String sortBy = jsonObject.get("colId").toString();
                query = query + " ORDER BY " + sortBy;
                if (jsonObject.get("isAsc").getAsBoolean()) {
                    query = query + " ASC";
                } else {
                    query = query + " DESC";
                }
            }
            String query1 = query;
            Integer endLimit = to - from;
            query = query + " LIMIT " + from + ", " + endLimit;
            System.out.println("query " + query);

            Query q = entityManager.createNativeQuery(query, Payhead.class);
            Query q1 = entityManager.createNativeQuery(query1, Payhead.class);

            payheadList = q.getResultList();
            System.out.println("Limit total rows " + payheadList.size());

            List<Payhead> payheadArrayList = new ArrayList<>();
            payheadArrayList = q1.getResultList();
            System.out.println("total rows " + payheadArrayList.size());

            if (payheadList.size() > 0) {
                for (Payhead payhead : payheadList) {
                    payheadDTOList.add(convertPayheadToPayheadDTO(payhead));
                }
            }
            genericDTData.setRows(payheadDTOList);
            genericDTData.setTotalRows(payheadArrayList.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception " + e.getMessage());

            genericDTData.setRows(payheadDTOList);
            genericDTData.setTotalRows(0);
        }
        return genericDTData;
    }

    private PayheadDTO convertPayheadToPayheadDTO(Payhead payhead) {
        PayheadDTO payheadDTO = new PayheadDTO();
        payheadDTO.setId(payhead.getId());
        payheadDTO.setName(payhead.getName());
        payheadDTO.setAmount(payhead.getAmount());
        payheadDTO.setPercentage(payhead.getPercentage());
         payheadDTO.setPayheadParentId(payhead.getPercentageOf() != null ? payhead.getPercentageOf().getId() : null);
         payheadDTO.setPayheadParentName(payhead.getPercentageOf() != null ? payhead.getPercentageOf().getName() : "");
        payheadDTO.setIsAdminRecord(payhead.getIsAdminRecord());
        payheadDTO.setCreatedAt(payhead.getCreatedAt() != null ? payhead.getCreatedAt().toString() : "");
        payheadDTO.setCreatedBy(payhead.getCreatedBy());
        payheadDTO.setUpdatedAt(payhead.getUpdatedAt() != null ? payhead.getUpdatedAt().toString() : "");
        payheadDTO.setUpdatedBy(payhead.getUpdatedBy());
        payheadDTO.setStatus(payhead.getStatus());
        payheadDTO.setPayheadStatus(payhead.getPayheadStatus());
        return payheadDTO;
    }

    public JsonObject payheadList() {
        JsonObject responseMessage = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        try {
            List<Payhead> payheadList = payheadRepository.findAllBystatus();
            for (Payhead payhead : payheadList) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", payhead.getId());
                jsonObject.addProperty("payheadName", payhead.getName());

                jsonArray.add(jsonObject);
            }
            responseMessage.add("response", jsonArray);
            responseMessage.addProperty("responseStatus", HttpStatus.OK.value());
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
            responseMessage.addProperty("response", "Failed to load data");
            responseMessage.addProperty("responseStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseMessage;
    }

    public Object findPayhead(Map<String, String> request) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Long payheadId = Long.parseLong(request.get("id"));
            Payhead payhead = payheadRepository.findByIdAndStatus(payheadId, true);
            if (payhead != null) {
                PayheadDTO payheadDTO = convertPayheadToPayheadDTO(payhead);
                responseMessage.setResponse(payheadDTO);
                responseMessage.setResponseStatus(HttpStatus.OK.value());
            } else {
                responseMessage.setMessage("Data not found");
                responseMessage.setResponseStatus(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception " + e.getMessage());
            responseMessage.setMessage("Failed to load data");
            responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseMessage;
    }

    public Object updatePayhead(Map<String, String> requestParam, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        Long id = Long.valueOf(requestParam.get("id"));
        Payhead payhead = payheadRepository.findByIdAndStatus(id, true);
        if (payhead != null) {
            Users users = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
            try {
                payhead.setName(requestParam.get("payheadName"));
                payhead.setPercentage(Double.parseDouble(requestParam.get("percentage")));
                payhead.setPayheadStatus(Boolean.parseBoolean(requestParam.get("payheadStatus")));

                if (requestParam.get("payheadId") != null) {
                    Long payheadId = Long.valueOf(requestParam.get("payheadId"));
                    Payhead payhead1 = payheadRepository.findByIdAndStatus(payheadId, true);
                    payhead.setPercentageOf(payhead1);
                }
                payhead.setUpdatedBy(users.getId());
                payhead.setInstitute(users.getInstitute());
                payhead.setUpdatedAt(LocalDateTime.now());
                payhead.setStatus(true);
                payhead.setIsAdminRecord(false);
                try {
                    payheadRepository.save(payhead);
                    responseMessage.setMessage("Payhead updated successfully");
                    responseMessage.setResponseStatus(HttpStatus.OK.value());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Exception " + e.getMessage());
                    responseMessage.setMessage("Failed to update payhead");
                    responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception " + e.getMessage());
                responseMessage.setMessage("Failed to update payhead");
                responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            responseMessage.setMessage("Data not found");
            responseMessage.setResponseStatus(HttpStatus.NOT_FOUND.value());
        }

        return responseMessage;
    }

    public Object deletePayhead(Map<String, String> requestParam, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        Long id = Long.valueOf(requestParam.get("id"));
        Payhead payhead = payheadRepository.findByIdAndStatus(id, true);
        if (payhead != null) {
            Users users = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
            try {
                payhead.setUpdatedBy(users.getId());
                payhead.setInstitute(users.getInstitute());
                payhead.setUpdatedAt(LocalDateTime.now());
                payhead.setStatus(false);
                try {
                    payheadRepository.save(payhead);
                    responseMessage.setMessage("Payhead deleted successfully");
                    responseMessage.setResponseStatus(HttpStatus.OK.value());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Exception " + e.getMessage());
                    responseMessage.setMessage("Failed to delete payhead");
                    responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception " + e.getMessage());
                responseMessage.setMessage("Failed to delete payhead");
                responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            responseMessage.setMessage("Data not found");
            responseMessage.setResponseStatus(HttpStatus.NOT_FOUND.value());
        }

        return responseMessage;
    }

    public JsonObject getpayheadList(HttpServletRequest request){
        JsonArray result =new JsonArray();
        Users users= jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
        List<Payhead> payheadList=new ArrayList<>();
        payheadList = payheadRepository.findAllBystatus();
        for(Payhead payhead:payheadList){
            try{
                JsonObject response=new JsonObject();
                response.addProperty("id",payhead.getId());
                response.addProperty("name",payhead.getName());
                response.addProperty("payheadStatus",payhead.getPayheadStatus());
                result.add(response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        JsonObject output=new JsonObject();
        output.addProperty("message","success");
        output.addProperty("responseStatus",HttpStatus.OK.value());
        output.add("data",result);
        return output;
    }
}
