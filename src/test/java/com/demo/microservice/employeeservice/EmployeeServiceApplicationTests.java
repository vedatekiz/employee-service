package com.demo.microservice.employeeservice;

import com.demo.microservice.employeeservice.entity.Employee;
import com.demo.microservice.employeeservice.entity.Hobby;
import com.demo.microservice.employeeservice.repository.HobbyRepository;
import com.demo.microservice.employeeservice.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceApplicationTests {

	@Autowired
	HobbyRepository hobbyRepository;

	@Autowired
	EmployeeService employeeService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetHobbyWhenFound(){
		Optional<Hobby> soccerHobby = hobbyRepository.findByHobbyIgnoreCase("soccer");
		Assert.assertEquals("soccer", soccerHobby.get().getHobby());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetHobbyWhenNotFound(){
		Optional<Hobby> soccerHobby = hobbyRepository.findByHobbyIgnoreCase("aaaa");
		Assert.assertEquals("aaaa", soccerHobby.get().getHobby());
	}

	@Test
	public void testSaveEmployee(){
		Employee employee = new Employee();
		employee.setFullName("Ali Vedat Ekiz");
		employee.setBirthDate(new Date());
		employee.setEmail("vedat.ekiz@gmail.com");

		Employee employeeSaved = employeeService.saveEmployee(employee);
		Assert.assertEquals(true,employeeSaved.getId() != null);
	}

}
