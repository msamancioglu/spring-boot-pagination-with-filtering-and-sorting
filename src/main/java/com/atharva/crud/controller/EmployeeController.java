package com.atharva.crud.controller;

import com.atharva.crud.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.atharva.crud.entity.Employee;
import com.atharva.crud.repository.specification.query.SearchRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController {

    public final EmployeeService employeeService;


    @PostMapping("/employees")
    public Page<Employee> test(@RequestBody SearchRequest request){

        return employeeService.getAllEmployees(request);
    }

        

    
}
