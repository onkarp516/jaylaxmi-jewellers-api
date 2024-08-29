package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.controller;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.google.gson.JsonObject;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AllocationController {
    @Autowired
    private AllocationService allocationService;
    @PostMapping(path = "/allocateAllowanceDeductions")
    public Object allocateAllowanceDeductions(HttpServletRequest request) {
        return allocationService.allocateAllowanceDeductions(request);
    }

    @PostMapping(path = "/getAllocationList")
    public Object getAllocationList(@RequestBody Map<String, String> jsonRequest, HttpServletRequest request) {
        JsonObject jsonObject = allocationService.getAllocationList(jsonRequest, request);
        return jsonObject.toString();
    }

    @PostMapping(path="/findAllocation")
    public Object findAllocation(@RequestBody Map<String,String> requestParam){
        JsonObject jsonObject = allocationService.findAllocation(requestParam);
        return  jsonObject.toString();
    }

    @PostMapping(path="/deleteAllocation")
    public Object deleteAllocation(@RequestBody Map<String,String> requestParam,HttpServletRequest request){
        return allocationService.deleteAllocation(requestParam,request);
    }
    @PostMapping(path = "/updateAllocation")
    public Object updateAllocation(@RequestBody Map<String, String> requestParam, HttpServletRequest request) {
        return allocationService.updateAllocation(requestParam, request);
    }

}
