package com.demo.microservice.employeeservice.controller;

import com.demo.microservice.employeeservice.boundary.ApiResponse;
import com.demo.microservice.employeeservice.boundary.EmployeeDTO;
import com.demo.microservice.employeeservice.entity.Employee;
import com.demo.microservice.employeeservice.repository.EmployeeRepository;
import com.demo.microservice.employeeservice.repository.HobbyRepository;
import com.demo.microservice.employeeservice.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("secured/employees")
public class EmployeeRestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    HobbyRepository hobbyRepository;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeRepository.findAll().stream().map(employee -> objectMapper.convertValue(employee, EmployeeDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse("Employee List", employeeDTOList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createEmployee(@RequestBody @Validated EmployeeDTO employeeDTO) {
        Employee employee = objectMapper.convertValue(employeeDTO, Employee.class);
        employee.setId(null);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        ApiResponse apiResponse = new ApiResponse("Employee saved Successfully", savedEmployee.getId());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(path = "{employeeId}")
    public ResponseEntity<ApiResponse> getEmployee(@PathVariable String employeeId) {
        Optional<Employee> employeeByUUID = employeeRepository.findById(UUID.fromString(employeeId));
        if (!employeeByUUID.isPresent()) {
            return new ResponseEntity<>(new ApiResponse("Employee Not Found With id: " + employeeId), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new ApiResponse("Employee Found ", objectMapper.convertValue(employeeByUUID.get(), EmployeeDTO.class)), HttpStatus.OK);
        }
    }

    @PutMapping(path = "{employeeId}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable String employeeId, @RequestBody @Validated EmployeeDTO employeeDTO) {
        Optional<Employee> employeeByUUID = employeeRepository.findById(UUID.fromString(employeeId));
        if (!employeeByUUID.isPresent()) {
            return new ResponseEntity<>(new ApiResponse("Employee Not Found With id: " + employeeId), HttpStatus.NOT_FOUND);
        } else {
            Employee employee = objectMapper.convertValue(employeeDTO, Employee.class);
            employee.setId(UUID.fromString(employeeId));
            employeeService.saveEmployee(employee);
            return new ResponseEntity<>(new ApiResponse("Employee Updated Successfully: " + employeeId), HttpStatus.OK);
        }

    }

    @DeleteMapping(path = "{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String employeeId) {
        ApiResponse apiResponse;
        try {
            employeeRepository.deleteById(UUID.fromString(employeeId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            apiResponse = new ApiResponse("Employee Delete Error. Detail: " + e.getLocalizedMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        apiResponse = new ApiResponse("Deleted Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
