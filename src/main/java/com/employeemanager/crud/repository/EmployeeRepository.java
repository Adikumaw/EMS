package com.employeemanager.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanager.crud.entity.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
