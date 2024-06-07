package com.employeemanager.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanager.crud.entity.Role;
import com.employeemanager.crud.service.RoleService;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "https://localhost")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getRoles();
    }

    @PutMapping
    public Role updateRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }
}
