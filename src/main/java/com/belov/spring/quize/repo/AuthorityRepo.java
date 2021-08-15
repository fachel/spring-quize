package com.belov.spring.quize.repo;

import com.belov.spring.quize.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authorities, Integer> {
    public Authorities getAuthorityByUser_id(Integer id);
}
