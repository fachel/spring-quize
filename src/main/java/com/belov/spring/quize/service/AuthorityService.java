package com.belov.spring.quize.service;


import com.belov.spring.quize.entity.Authorities;

public interface AuthorityService {
    public Authorities getAuthorityByUserId(Integer id);
}
