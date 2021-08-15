package com.belov.spring.quize.service;

import com.belov.spring.quize.entity.User;
import com.belov.spring.quize.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public User getUserByName(String name) {
        return userRepository.getUserByUsername(name);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
