package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.controller;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.EmployeeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EmployeeDocumentController {
    @Autowired
    EmployeeDocumentService employeeDocumentService;

    @PostMapping(path = "/create_emp_document")
    public ResponseEntity<?> createEmployeeDocument(HttpServletRequest request) {
        return ResponseEntity.ok(employeeDocumentService.createEmployeeDocument(request));
    }
}
