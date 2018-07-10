package com.demo.microservice.employeeservice.service.impl;

import com.demo.microservice.employeeservice.entity.Employee;
import com.demo.microservice.employeeservice.entity.Hobby;
import com.demo.microservice.employeeservice.repository.EmployeeRepository;
import com.demo.microservice.employeeservice.repository.HobbyRepository;
import com.demo.microservice.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if(employee.getHobbies() != null && !employee.getHobbies().isEmpty()){
            List<Hobby> hobbies = employee.getHobbies().stream().collect(Collectors.toList());
            employee.getHobbies().clear();
            if(!Objects.isNull(hobbies) && !hobbies.isEmpty()){
                for(Hobby hobby : hobbies){
                    Optional<Hobby> byHobby = hobbyRepository.findByHobbyIgnoreCase(hobby.getHobby());
                    if(byHobby.isPresent()){
                        employee.getHobbies().add(byHobby.get());
                    }
                    else{
                        Hobby newHobby = new Hobby();
                        newHobby.setHobby(hobby.getHobby());
                        employee.getHobbies().add(newHobby);
                    }
                }
            }
        }

        return employeeRepository.save(employee);
    }
}
