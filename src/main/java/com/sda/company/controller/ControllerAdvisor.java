package com.sda.company.controller;

import com.sda.company.exception.CompanyException;
import com.sda.company.exception.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    //@ExceptionHandler - ii atribuim ca si parametru clasa custom creata de noi, in cazul de fata CompanyException.class
    @ExceptionHandler(CompanyException.class)
    public ResponseEntity<Object> handlerCompanyException(CompanyException companyException) {
        Map<String, Object> body = new LinkedHashMap<>();
        // ne va afisa timpul local la care va avea loc eroarea
        body.put("Timestamp", LocalDateTime.now());
        body.put("Error message:", companyException.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<Object> handlerCompanyException(EmployeeException employeeException) {
        Map<String, Object> body = new LinkedHashMap<>();
        // ne va afisa timpul local la care va avea loc eroarea
        body.put("Timestamp", LocalDateTime.now());
        body.put("Error message:", employeeException.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);

    }
}
