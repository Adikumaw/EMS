package com.employeemanager.crud.service;

import java.util.List;

import com.employeemanager.crud.entity.User;

public interface UserService {
    User saveUser(User user);

    List<User> getUsers();

    User findByEmail(String email);
}
