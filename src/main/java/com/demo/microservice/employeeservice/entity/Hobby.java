package com.demo.microservice.employeeservice.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Hobby extends AbstractPersistable<Long> {

    @Column
    private String hobby;


    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
