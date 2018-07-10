package com.demo.microservice.employeeservice.boundary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

    private String id;

    @NotNull(message = "fullName should not be empty")
    private String fullName;

    @NotNull(message = "email should not be empty")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private Set<HobbyDTO> hobbies = new TreeSet<HobbyDTO>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }
}
