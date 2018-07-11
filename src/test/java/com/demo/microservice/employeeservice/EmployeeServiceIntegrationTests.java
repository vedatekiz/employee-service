package com.demo.microservice.employeeservice;

import com.demo.microservice.employeeservice.boundary.EmployeeDTO;
import com.demo.microservice.employeeservice.boundary.HobbyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EmployeeServiceApplication.class)
@AutoConfigureMockMvc
public class EmployeeServiceIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void whenCreateEmployee_thenStatus201() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFullName("Ali Vedat Ekiz");
        employeeDTO.setEmail("vedat.ekiz@yahoo.com");
        employeeDTO.setBirthDate(new Date());

        Set<HobbyDTO> hobbySet = new HashSet<>();
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setHobby("swimming");
        hobbySet.add(hobbyDTO);

        employeeDTO.setHobbies(hobbySet);

        String json = mapper.writeValueAsString(employeeDTO);

        mvc.perform(post("/secured/employees")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenCreateEmployeeWithExistingEmail_thenStatus400() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFullName("Ali Vedat Ekiz");
        employeeDTO.setEmail("vedat_ekiz@yahoo.com");
        employeeDTO.setBirthDate(new Date());

        Set<HobbyDTO> hobbySet = new HashSet<>();
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setHobby("swimming");
        hobbySet.add(hobbyDTO);

        employeeDTO.setHobbies(hobbySet);

        String json = mapper.writeValueAsString(employeeDTO);

        mvc.perform(post("/secured/employees")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        employeeDTO = new EmployeeDTO();
        employeeDTO.setFullName("Domagoj Vida");
        employeeDTO.setEmail("vedat_ekiz@yahoo.com");
        employeeDTO.setBirthDate(new Date());

        json = mapper.writeValueAsString(employeeDTO);

        mvc.perform(post("/secured/employees")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenListEmployees_thenStatus200() throws Exception {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFullName("Ali Vedat Ekiz");
        employeeDTO.setEmail("vedat.ekiz@yahoo.com");
        employeeDTO.setBirthDate(new Date());

        Set<HobbyDTO> hobbySet = new HashSet<>();
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setHobby("swimming");
        hobbySet.add(hobbyDTO);

        employeeDTO.setHobbies(hobbySet);

        String json = mapper.writeValueAsString(employeeDTO);

        mvc.perform(post("/secured/employees")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        mvc.perform(get("/secured/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseObject[0].fullName", is("Ali Vedat Ekiz")));
    }
}
