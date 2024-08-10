package com.javacurd.springboot.service;

import com.javacurd.springboot.exception.EmployeeAlreadyExistException;
import com.javacurd.springboot.exception.NoSuchEmployeeException;
import com.javacurd.springboot.model.Employee;
import com.javacurd.springboot.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee(){
        List<Employee> employee =  employeeRepository.findAll();
        if (employee.isEmpty()){
            throw new NoSuchEmployeeException("No Data Found");
        }
        return employee;
    }

    public Employee createEmployee(Employee employee){
        if(employeeRepository.findByEmailId(employee.getEmailId()).isPresent()){
            throw new EmployeeAlreadyExistException("Already Exist employee");
        }
        return employeeRepository.save(employee);

    }

    public Employee getEmployeeById(Long id)
    {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new NoSuchEmployeeException("Employee Not Found:" + id));
        return employee;
    }

    public void deleteEmployee(Long id)
    {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new NoSuchEmployeeException("Employee Not Found:" + id));
        employeeRepository.delete(employee);
    }

    public  void deleteAllEmployee(){
            employeeRepository.deleteAll();
    }


    public Employee updateEmployee(Long id, Employee employeeDetails)
    {
        Employee updateEmployee = employeeRepository.findById(id).
                orElseThrow(()-> new NoSuchEmployeeException("Employee Not Found:" + id));
        if(employeeDetails.getFirstName() != null){
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        }
        if(employeeDetails.getLastName() != null){
        updateEmployee.setLastName(employeeDetails.getLastName());
        }
        if (employeeDetails.getEmailId() != null){
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        }

        return employeeRepository.save(updateEmployee);
    }
}
