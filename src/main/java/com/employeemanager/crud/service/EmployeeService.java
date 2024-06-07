package com.employeemanager.crud.service;

import java.util.List;

import com.employeemanager.crud.entity.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getEmployees();

    Employee getEmployeeById(int id);

    Employee updateEmployee(Employee employee);

    String deleteEmployeeById(int employeeId);

}
