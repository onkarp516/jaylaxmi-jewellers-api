package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.service;

import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.repository.EmployeeEducationRepository;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.response.ResponseMessage;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.EmployeeEducation;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.model.Users;
import com.truethic.jaylaxmi.JaylaxmiJewellersAPI.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class EmployeeEducationService {
    @Autowired
    EmployeeEducationRepository employeeEducationRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public Object createEmployeeEducation(HttpServletRequest request) {
        ResponseMessage responseObject = new ResponseMessage();
        EmployeeEducation employeeEducation = new EmployeeEducation();
        employeeEducation.setDesignationName(request.getParameter("designationName"));
        employeeEducation.setSchoolName(request.getParameter("schoolName"));
        employeeEducation.setYear(Integer.parseInt(request.getParameter("year")));
        employeeEducation.setGrade(request.getParameter("grade"));
        employeeEducation.setPercentage(request.getParameter("percentage"));
        employeeEducation.setMainSubject(request.getParameter("mainSubject"));
        employeeEducation.setStatus(true);
        if (request.getHeader("Authorization") != null) {
            Users user = jwtTokenUtil.getUserDataFromToken(request.getHeader("Authorization").substring(7));
            employeeEducation.setCreatedBy(user.getId());
            employeeEducation.setInstitute(user.getInstitute());
        }
        try {
            employeeEducationRepository.save(employeeEducation);
            responseObject.setMessage("Employee Education added successfully");
            responseObject.setResponseStatus(HttpStatus.OK.value());
        } catch (Exception e) {

            responseObject.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseObject.setMessage("Internal Server Error");
            e.printStackTrace();
            System.out.println("Exception:" + e.getMessage());
        }
        return responseObject;
    }
}
