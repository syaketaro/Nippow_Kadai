package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    
    public  AuthenticationService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;
    }
    
    public List<Authentication> getAuthenticationList() {
        
        return authenticationRepository.findAll();
    }
    
    public Authentication getAuthentication(Integer employee_id) {
        return authenticationRepository.findById(employee_id).get();
    }
    
    
    @Transactional
    public Authentication saveAuthentication(Authentication authentication) {
        return authenticationRepository.save(authentication);
    }
}
