package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.controller;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkController {
    @Autowired
    private WorkService workService;

    @PostMapping(path = "/getEmployeesWork")
    public Object getEmployeesWork() {
        return workService.getEmployeesWork();
    }
}
