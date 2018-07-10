package com.demo.microservice.employeeservice.boundary;

public class ApiResponse {

    private String responseDescription;
    private Object responseObject;

    public ApiResponse(String responseDescription, Object responseObject) {
        this.responseDescription = responseDescription;
        this.responseObject = responseObject;
    }

    public ApiResponse(Object responseObject) {
        this.responseObject = responseObject;
    }

    public ApiResponse(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}
