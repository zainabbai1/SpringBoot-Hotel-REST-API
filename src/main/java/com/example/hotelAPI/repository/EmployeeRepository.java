package com.example.hotelAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelAPI.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long>
{}
