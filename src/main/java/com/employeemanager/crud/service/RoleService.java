package com.employeemanager.crud.service;

import java.util.List;

import com.employeemanager.crud.entity.Role;

public interface RoleService {
    Role saveRole(Role role);

    List<Role> getRoles();
}
