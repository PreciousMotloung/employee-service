package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> fetchAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
//        employee.setId(null);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee employeeObj = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employee.getId()));

        if (employeeObj != null) {
            employeeObj.setEmail(employee.getEmail());
            employeeObj.setName(employee.getName());
            employeeObj.setSalary(employee.getSalary());
        }

        return employeeRepository.save(employeeObj);
    }

    @Override
    public String deleteEmployee(Long id) {
        Employee employeeObj = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        employeeRepository.delete(employeeObj);

        return "Employee Deleted Successfully for id " + id;
    }
}
