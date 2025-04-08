package com.atharva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.atharva.crud.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>,  JpaSpecificationExecutor<Employee> {    
}


