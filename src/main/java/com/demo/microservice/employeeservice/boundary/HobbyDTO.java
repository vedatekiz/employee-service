package com.demo.microservice.employeeservice.boundary;

import javax.validation.constraints.NotNull;

public class HobbyDTO {

    @NotNull(message = "hobby should not be empty")
    private String hobby;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
