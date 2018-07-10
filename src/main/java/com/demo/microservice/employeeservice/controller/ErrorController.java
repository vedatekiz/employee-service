package com.demo.microservice.employeeservice.controller;

import com.demo.microservice.employeeservice.boundary.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn("Method Arguments Not Valid");
        ApiResponse apiResponse;
        List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        if (Objects.isNull(allErrors) || allErrors.isEmpty()) {
            apiResponse = new ApiResponse("Method Arguments Not Valid");
        } else {
            List<String> errorList = allErrors.stream().map(objectError -> objectError.getDefaultMessage() + " on object " + objectError.getObjectName()).collect(Collectors.toList());
            apiResponse = new ApiResponse("Method Arguments Not Valid", errorList);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleException(DataIntegrityViolationException e) {
        ApiResponse apiResponse = new ApiResponse(e.getCause().getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
