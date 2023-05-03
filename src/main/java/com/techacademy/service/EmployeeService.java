package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }
    
    public List<Employee> getEmployeeList() {
        
        return employeeRepository.findAll();
    }
    
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }
    
    
    @Transactional
    
    public Employee saveEmployee(Employee employee) {
//        ↓↓ここ全くわからないポイント　外部キーとの連結の処理、　getter,setterの復習
        employee.getAuthentication().setEmployee(employee);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        
        String pass = employee.getAuthentication().getPassword();
        
        employee.getAuthentication().setPassword(passwordEncoder.encode(pass));

        
        return employeeRepository.save(employee);
    }
    
//    @Transactional
//    public void deleteEmployee(Set<Integer> idck) {
//            employeeRepository.deleteById(idck);
//        
//    }
}
