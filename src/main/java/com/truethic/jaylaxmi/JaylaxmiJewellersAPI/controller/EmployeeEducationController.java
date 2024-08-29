package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.controller;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.EmployeeEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EmployeeEducationController {
    @Autowired
    EmployeeEducationService employeeEducationService;

    @PostMapping(path = "/create_empEducation")
    public ResponseEntity<?> createEmployeeEducation(HttpServletRequest request) {
        return ResponseEntity.ok(employeeEducationService.createEmployeeEducation(request));
    }


}
