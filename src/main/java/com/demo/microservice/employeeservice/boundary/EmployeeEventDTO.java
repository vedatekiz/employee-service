package com.demo.microservice.employeeservice.boundary;

import java.util.Date;

public class EmployeeEventDTO {

    private String employeeId;
    private OpType opType;
    private Date opDate;

    public EmployeeEventDTO(String employeeId, OpType opType, Date opDate) {
        this.employeeId = employeeId;
        this.opType = opType;
        this.opDate = opDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }

    public OpType getOpType() {
        return opType;
    }

    public void setOpType(OpType opType) {
        this.opType = opType;
    }
}
