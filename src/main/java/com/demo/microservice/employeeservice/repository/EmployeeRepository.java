package com.demo.microservice.employeeservice.repository;

import com.demo.microservice.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

}
