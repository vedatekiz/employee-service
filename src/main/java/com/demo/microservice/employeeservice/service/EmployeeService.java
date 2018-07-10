package com.demo.microservice.employeeservice.service;

import com.demo.microservice.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee saveEmployee(Employee employee);

}
