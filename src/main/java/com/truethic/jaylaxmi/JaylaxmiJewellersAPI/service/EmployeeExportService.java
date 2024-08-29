package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.EmployeeExportRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.EmployeeExport;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Users;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EmployeeExportService {
    @Autowired
    private EmployeeExportRepository employeeRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public Object createEmployee(HttpServletRequest request) {

        try {
            EmployeeExport employee = new EmployeeExport();
            employee.setEmpName(request.getParameter("empName"));
            employee.setDeptName(request.getParameter("deptName"));
            if (request.getHeader("Authorization") != null) {
                Users user = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
                employee.setInstitute(user.getInstitute());
            }
            employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception:" + e.getMessage());
        }
        return "Employee Created";
    }

    public List<EmployeeExport> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
