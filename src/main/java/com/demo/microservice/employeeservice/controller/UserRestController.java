package com.demo.microservice.employeeservice.controller;

import com.demo.microservice.employeeservice.boundary.UserDTO;
import com.demo.microservice.employeeservice.entity.User;
import com.demo.microservice.employeeservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/signin")
    @ApiOperation(value = "Authenticates user and returns its JWT token")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(
                        @ApiParam("Username") @RequestParam String username,
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "Creates user and returns its JWT token")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Username is already in use"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String signup(@ApiParam("Signup User") @RequestBody UserDTO user) {
        User userDO = objectMapper.convertValue(user, User.class);
        return userService.signup(userDO);
    }
}
