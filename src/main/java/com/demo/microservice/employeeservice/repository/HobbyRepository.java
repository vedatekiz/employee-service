package com.demo.microservice.employeeservice.repository;

import com.demo.microservice.employeeservice.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByHobbyIgnoreCase(String hobby);
}
