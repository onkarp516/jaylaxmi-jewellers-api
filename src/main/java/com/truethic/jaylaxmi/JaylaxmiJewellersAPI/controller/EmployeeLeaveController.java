package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.controller;

import com.google.gson.JsonObject;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.EmployeeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class EmployeeLeaveController {
    @Autowired
    private EmployeeLeaveService employeeLeaveService;

    /*Mobile app urls start*/

    @PostMapping(path = "/mobile/applyLeave")
    public Object applyLeave(@RequestBody Map<String, String> requestParam, HttpServletRequest request) {
        return employeeLeaveService.applyLeave(requestParam, request);
    }

    @PostMapping(path = "/mobile/checkLeaveAvailability")
    public Object checkLeaveAvailability(@RequestBody Map<String, String> requestParam, HttpServletRequest request) {
        JsonObject res = employeeLeaveService.checkLeaveAvailability(requestParam, request);
        return  res.toString();
    }

    @GetMapping(path = "/mobile/listOfLeaves")
    public Object listOfLeaves(HttpServletRequest request) {
        JsonObject res = employeeLeaveService.listOfLeaves(request);
        return res.toString();
    }
    /*Mobile app urls end*/

    @PostMapping(path = "/DTEmployeeLeaves")
    public Object DTEmployeeLeaves(@RequestBody Map<String, String> request, HttpServletRequest httpServletRequest) {
        return employeeLeaveService.DTEmployeeLeaves(request, httpServletRequest);
    }

    @PostMapping(path = "/updateEmployeeLeaveStatus")
    public Object updateEmployeeLeaveStatus(@RequestBody Map<String, String> jsonRequest, HttpServletRequest request) {
        return employeeLeaveService.updateEmployeeLeaveStatus(jsonRequest, request);
    }

    @GetMapping(path = "/mobile/getEmployeesLeaveRequests")
    public Object getEmployeesLeaveRequests(HttpServletRequest request) {
        JsonObject res = employeeLeaveService.getEmployeesLeaveRequests(request);
        return res.toString();
    }

    @PostMapping(path = "/mobile/approveEmpLeaves")
    public Object approveEmpLeaves(@RequestBody Map<String, String> jsonRequest, HttpServletRequest request) {
        return employeeLeaveService.approveEmpLeaves(jsonRequest, request);
    }
}
