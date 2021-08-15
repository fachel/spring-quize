package com.belov.spring.quize.repo;

import com.belov.spring.quize.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User getUserByUsername(String name);
}
