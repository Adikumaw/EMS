package com.employeemanager.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanager.crud.entity.Employee;
import com.employeemanager.crud.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        Optional<Employee> fetchedEmployee = employeeRepository.findById(employee.getId());

        if (fetchedEmployee.isPresent()) {
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    @Override
    public String deleteEmployeeById(int employeeId) {
        try {
            Optional<Employee> fetchedEmployee = employeeRepository.findById(employeeId);

            if (fetchedEmployee.isPresent()) {
                employeeRepository.delete(fetchedEmployee.get());
                return "Employee " + fetchedEmployee.get().getId() + " has been deleted";
            }

            return "Employee " + employeeId + " not found!";
        } catch (Exception e) {
            return "Employee " + employeeId + " not found!";
        }
    }

}
