package com.atharva.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atharva.crud.entity.Employee;
import com.atharva.crud.repository.EmployeeRepository;
import com.atharva.crud.repository.specification.query.SearchRequest;
import com.atharva.crud.repository.specification.query.SearchSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    

    public Page<Employee> getAllEmployees(SearchRequest request) {
        SearchSpecification<Employee> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getPageSize());
        return employeeRepository.findAll(specification, pageable);
    }


}
