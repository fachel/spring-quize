package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.User;

public interface UserService {
    public User getUserByName(String name);
    public User getUserById(Integer id);
    public void save(User user);
}
