package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.dto.GenericDTData;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.*;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.*;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.response.ResponseMessage;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.JwtTokenUtil;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AllocationService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AllowanceRepository allowanceRepository;
    @Autowired
    private DeductionRepository deductionRepository;
    @Autowired
    Utility utility;
    @Autowired
    private PayheadRepository payheadRepository;

    public Object allocateAllowanceDeductions(HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Users users = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
            Long employeeId = Long.parseLong(request.getParameter("employeeId"));
            Employee employee = employeeRepository.findByIdAndStatus(employeeId, true);
            try {
            String allocationsStr = request.getParameter("allocations");
                if (allocationsStr != null) {
                    JsonArray allocations = new JsonParser().parse(allocationsStr).getAsJsonArray();

                    for (int i = 0; i < allocations.size(); i++) {
                        JsonObject mObject = allocations.get(i).getAsJsonObject();
                        if(mObject.has("amount") && !mObject.get("amount").getAsString().equals("")){
                            Allocation allocation = new Allocation();
                            allocation.setAmount(mObject.get("amount").getAsDouble());
                            allocation.setStatus(true);
                            allocation.setCreatedBy(users.getId());
                            allocation.setCreatedAt(LocalDate.now());
                            allocation.setInstitute(users.getInstitute());
                            allocation.setAllocationDate(LocalDate.parse(request.getParameter("allocationDate")));
                            allocation.setIsAllowance(Boolean.parseBoolean(request.getParameter("isAllowance")));
                            allocation.setEmployee(employee);
                            if(Boolean.parseBoolean(request.getParameter("isAllowance"))) {
                                Payhead payhead = payheadRepository.findByIdAndStatus(mObject.get("id").getAsLong(), true);
                                allocation.setPayhead(payhead);
                                allocation.setDeduction(null);
                            } else {
                                Deduction deduction = deductionRepository.findByIdAndStatus(mObject.get("id").getAsLong(), true);
                                allocation.setDeduction(deduction);
                                allocation.setPayhead(null);
                            }
                            allocationRepository.save(allocation);
                        }
                    }
                }
                responseMessage.setMessage("Allocation Completed successfully");
                responseMessage.setResponseStatus(HttpStatus.OK.value());
            } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
                e.printStackTrace();
                responseMessage.setMessage("Failed to create allocation");
                responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
            responseMessage.setMessage("Failed to create allocation");
            responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseMessage;
    }

    public JsonObject getAllocationList(Map<String, String> jsonRequest, HttpServletRequest request) {
        System.out.println("jsonRequest " + jsonRequest);
        JsonObject response = new JsonObject();
        JsonObject res = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        Users users = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));

        try {
            System.out.println("jsonRequest " + jsonRequest.get("currentMonth"));
            String[] currentMonth = jsonRequest.get("currentMonth").split("-");
            String userMonth = currentMonth[1];
            String userYear = currentMonth[0];
            String userDay = "01";

            int cYear = LocalDate.now().getYear();
            int uYear = Integer.parseInt(userYear);
            if(cYear != uYear){
                response.addProperty("message", "Invalid Year");
                response.addProperty("responseStatus", HttpStatus.NOT_ACCEPTABLE.value());
                return response;
            }

            String newUserDate = userYear + "-" + userMonth + "-" + userDay;
            System.out.println("newUserDate" + newUserDate);
            LocalDate currentDate = LocalDate.parse(newUserDate);

            System.out.println("currentDate" + currentDate);
            LocalDate firstDateOfMonth = currentDate.withDayOfMonth(1);
            System.out.println("firstDateOfMonth" + firstDateOfMonth);
            LocalDate lastDateOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth()).plusDays(1);
            System.out.println("lastDateOfMonth " + lastDateOfMonth);

            if (jsonRequest.get("employeeId").equalsIgnoreCase("all")) {
                List<Employee> employees = employeeRepository.findByInstituteIdAndStatusOrderByFirstNameAsc(users.getInstitute().getId(), true);

                for (Employee employee1 : employees) {

                    JsonObject empObj = new JsonObject();
                    JsonArray allocationArray = new JsonArray();

                    empObj.addProperty("id", employee1.getId());
                    empObj.addProperty("employeeName", utility.getEmployeeName(employee1));
                    Double payheadAmount = 0.0;
                    Double deductionAmount = 0.0;
                    List<Allocation> allocationList = allocationRepository.getEmployeeAllocations(employee1.getId(),String.valueOf(firstDateOfMonth), String.valueOf(lastDateOfMonth));
                    for(Allocation allocation : allocationList){
                        JsonObject allocationObject = new JsonObject();
                        allocationObject.addProperty("id", allocation.getId());
                        allocationObject.addProperty("allocation_date", allocation.getAllocationDate().toString());
                        allocationObject.addProperty("amount", allocation.getAmount());
                        allocationObject.addProperty("created_at", allocation.getCreatedAt().toString());
                        allocationObject.addProperty("is_allowance", allocation.getIsAllowance());
                        if(allocation.getIsAllowance()){
                            Payhead payhead = payheadRepository.findByIdAndStatus(allocation.getPayhead().getId(),true);
                            allocationObject.addProperty("name", payhead.getName());
                            payheadAmount += allocation.getAmount();
                        } else {
                            Deduction deduction = deductionRepository.findByIdAndStatus(allocation.getDeduction().getId(),true);
                            allocationObject.addProperty("name", deduction.getName());
                            deductionAmount += allocation.getAmount();
                        }
                        allocationArray.add(allocationObject);
                    }
                    empObj.addProperty("payhead_amount",payheadAmount);
                    empObj.addProperty("deduction_amount",deductionAmount);
                    empObj.add("allocationList", allocationArray);
                    jsonArray.add(empObj);
                }
            } else {
                Long employeeId = Long.valueOf(jsonRequest.get("employeeId"));
                Employee employee = employeeRepository.findByIdAndInstituteIdAndStatus(employeeId, users.getInstitute().getId(), true);

                if (employee != null) {
                    JsonObject empObj = new JsonObject();
                    JsonArray allocationArray = new JsonArray();
                    empObj.addProperty("id", employee.getId());
                    empObj.addProperty("employeeName", utility.getEmployeeName(employee));
                    Double payheadAmount = 0.0;
                    Double deductionAmount = 0.0;
                    List<Allocation> allocationList = allocationRepository.getEmployeeAllocations(employee.getId(),String.valueOf(firstDateOfMonth), String.valueOf(lastDateOfMonth));
                    for(Allocation allocation : allocationList){
                        JsonObject allocationObject = new JsonObject();
                        allocationObject.addProperty("id", allocation.getId());
                        allocationObject.addProperty("allocation_date", allocation.getAllocationDate().toString());
                        allocationObject.addProperty("amount", allocation.getAmount());
                        allocationObject.addProperty("created_at", allocation.getCreatedAt().toString());
                        allocationObject.addProperty("is_allowance", allocation.getIsAllowance());
                        if(allocation.getIsAllowance()){
                            Payhead payhead = payheadRepository.findByIdAndStatus(allocation.getPayhead().getId(),true);
                            allocationObject.addProperty("name", payhead.getName());
                            payheadAmount += allocation.getAmount();
                        } else {
                            Deduction deduction = deductionRepository.findByIdAndStatus(allocation.getDeduction().getId(),true);
                            allocationObject.addProperty("name", deduction.getName());
                            deductionAmount += allocation.getAmount();
                        }
                        allocationArray.add(allocationObject);
                    }
                    empObj.addProperty("payhead_amount",payheadAmount);
                    empObj.addProperty("deduction_amount",deductionAmount);
                    empObj.add("allocationList", allocationArray);
                    jsonArray.add(empObj);
                }
            }
            res.add("allocationData", jsonArray);
            response.add("response", res);
            response.addProperty("responseStatus", HttpStatus.OK.value());
        } catch (Exception e) {
            System.out.println("exception  " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    public Object updateAllocation(Map<String, String> requestParam, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Users users = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
            Long id = Long.valueOf(requestParam.get("id"));
            Allocation allocation = allocationRepository.findByIdAndStatus(id, true);
            if (allocation != null) {
                allocation.setAmount(Double.valueOf(requestParam.get("amount")));
                allocation.setAllocationDate(LocalDate.parse(requestParam.get("allocation_date")));
                allocation.setUpdatedBy(users.getId());
                allocation.setUpdatedAt(LocalDate.now());
                try {
                    allocationRepository.save(allocation);
                    responseMessage.setMessage("Allocation updated successfully");
                    responseMessage.setResponseStatus(HttpStatus.OK.value());
                } catch (Exception e) {
                    System.out.println("Exception " + e.getMessage());
                    e.printStackTrace();
                    responseMessage.setMessage("Failed to update Allocation");
                    responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } else {
                responseMessage.setMessage("Data not found");
                responseMessage.setResponseStatus(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
            responseMessage.setMessage("Failed to update Allocation");
            responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseMessage;
    }

    public JsonObject findAllocation(Map<String, String> requestParam){
        JsonObject responseMessage=new JsonObject();
        try{
            Long id= Long.valueOf(requestParam.get("id"));
            Allocation allocation=allocationRepository.findByIdAndStatus(id, true);
            JsonObject allocationObject = new JsonObject();
            if(allocation != null){
                if(allocation.getIsAllowance()){
                    Payhead payhead = payheadRepository.findByIdAndStatus(allocation.getPayhead().getId(), true);
                    allocationObject.addProperty("allocation_name",payhead.getName());
                } else {
                    Deduction deduction = deductionRepository.findByIdAndStatus(allocation.getDeduction().getId(), true);
                    allocationObject.addProperty("allocation_name",deduction.getName());
                }
                allocationObject.addProperty("id",allocation.getId());
                allocationObject.addProperty("allocation_date",allocation.getAllocationDate().toString());
                allocationObject.addProperty("amount",allocation.getAmount());
                allocationObject.addProperty("isAllowance",allocation.getIsAllowance());
                responseMessage.add("response",allocationObject);
                responseMessage.addProperty("responseStatus", HttpStatus.OK.value());
            } else {
                responseMessage.addProperty("response","Data not found");
                responseMessage.addProperty("responseStatus",HttpStatus.NOT_FOUND.value());
            }
        }catch(Exception e){
            System.out.println("Exception " + e.getMessage());
            e.printStackTrace();
            responseMessage.addProperty("response","Failed to load data");
            responseMessage.addProperty("responseStatus",HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseMessage;
    }

    public Object deleteAllocation(Map<String,String> requestParam,HttpServletRequest request){
        ResponseMessage responseMessage=new ResponseMessage();
        try{
            Users users=jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
            Long id=Long.valueOf(requestParam.get("id"));
            Allocation allocation=allocationRepository.findByIdAndStatus(id,true);
            if(allocation!=null){
                allocation.setStatus(false);
                allocation.setUpdatedBy(users.getId());
                allocation.setUpdatedAt(LocalDate.now());
                allocation.setInstitute(users.getInstitute());
                try{
                    allocationRepository.save(allocation);
                    responseMessage.setMessage("Allocation deleted Successfully");
                    responseMessage.setResponseStatus(HttpStatus.OK.value());
                }catch (Exception e){
                    e.printStackTrace();
                    responseMessage.setMessage("Failed to delete Allocation");
                    responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            }else {
                responseMessage.setMessage("Data Not Found");
                responseMessage.setResponseStatus(HttpStatus.NOT_FOUND.value());
            }
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("Failed to delete Allocation");
            responseMessage.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseMessage;
    }
}
