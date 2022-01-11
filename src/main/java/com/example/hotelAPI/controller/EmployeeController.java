package com.example.hotelAPI.controller;

import com.example.hotelAPI.exception.ResourceNotFoundException;
import com.example.hotelAPI.model.Employee;
import com.example.hotelAPI.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController 
{
    @Autowired
    EmployeeRepository employeeRepository;

    // Get All Employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() 
    {
        return employeeRepository.findAll();
    }

    // Create a new Employees
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) 
    {
        return (Employee) employeeRepository.save(employee);
    }

    // Get a Single Employees
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) throws Throwable
    {
        return (Employee) employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    // Update a Employee
    @PutMapping("/employees/{id}")
    public Employee updateReservation(@PathVariable(value = "id") Long employeeId,
                                         @Valid @RequestBody Employee employeeDetails) throws Throwable
    {

        Employee employee = (Employee) employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employee.setName(employeeDetails.getName());
        employee.setSurname(employeeDetails.getSurname());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPassword(employeeDetails.hashPassword(employeeDetails.getPassword()));

        Employee updatedEmployee = (Employee) employeeRepository.save(employee);
        return updatedEmployee;
    }

    // Delete a Employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws Throwable 
    {
        Employee employee = (Employee) employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }

}