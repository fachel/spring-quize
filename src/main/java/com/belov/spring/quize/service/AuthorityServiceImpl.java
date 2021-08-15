package com.belov.spring.quize.service;

import com.belov.spring.quize.repo.AuthorityRepo;
import com.belov.spring.quize.entity.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityRepo authorityRepo;

    @Override
    public Authorities getAuthorityByUserId(Integer id) {
        return authorityRepo.getAuthorityByUser_id(id);
    }
}
