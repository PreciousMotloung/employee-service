package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        Employee employeeObj = employeeRepository.findById(employee.getId()).get();

        if(employeeObj != null)
        {
            employeeObj.setEmail(employee.getEmail());
            employeeObj.setName(employee.getName());
            employeeObj.setSalary(employee.getSalary());
        }

        return employeeRepository.save(employeeObj);
    }

    @Override
    public String deleteEmployee(Long id) {

        Employee employeeObj = employeeRepository.findById(id).get();
        String deleteMsg = null;

        if(employeeObj != null)
        {
            employeeRepository.delete(employeeObj);
            deleteMsg = "Employee Deleted Successfully for id "+id;
        }
        return deleteMsg;
    }
}
