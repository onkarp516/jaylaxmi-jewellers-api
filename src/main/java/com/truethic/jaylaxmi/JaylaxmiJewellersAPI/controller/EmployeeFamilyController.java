package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.controller;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.EmployeeFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EmployeeFamilyController {

    @Autowired
    EmployeeFamilyService employeeFamilyService;

    @PostMapping(path = "/create_empFamily")
    public ResponseEntity<?> createEmployeeFamily(HttpServletRequest request) {
        return ResponseEntity.ok(employeeFamilyService.createEmployeeFamily(request));
    }
}
