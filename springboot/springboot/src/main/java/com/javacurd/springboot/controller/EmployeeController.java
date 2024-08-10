package com.javacurd.springboot.controller;

import com.javacurd.springboot.model.Employee;
import com.javacurd.springboot.repository.EmployeeRepository;
import com.javacurd.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> allEmployee = employeeService.getAllEmployee();
        return new ResponseEntity<>(allEmployee,HttpStatus.OK);
    }

    //create employee API
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.createEmployee(employee);
        return new ResponseEntity<>(employee1,HttpStatus.CREATED);
    }

    //get employee by id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.ACCEPTED);
    }

    //update employee API
    @PatchMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee employee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(employee);
    }

    //delete employee API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployee() {
        employeeService.deleteAllEmployee();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
