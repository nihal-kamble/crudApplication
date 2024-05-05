package com.nihalkamble.springboot.crudApplication.controller;

import com.nihalkamble.springboot.crudApplication.model.Employee;
import com.nihalkamble.springboot.crudApplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/addEmployee")
    public Long addEmployee(@RequestBody Employee employee) {
        Employee employeeToBeAdded = new Employee();
        employeeToBeAdded.setEmailId(employee.getEmailId());
        employeeToBeAdded.setFirstName(employee.getFirstName());
        employeeToBeAdded.setLastName(employee.getLastName());
        employeeRepository.save(employeeToBeAdded);
        return employeeToBeAdded.getId();
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).get();

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
        //Changes
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee with Id " + id + "has been deleted";
    }
}
