package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    
    @Id
    @Column(length = 20, nullable = false)
    private String code;
    
    @Column(length = 255, nullable = false)
    private String password;
    
    @Column(length = 10, nullable = false)
    private String role;
    
    @Column(nullable = false)
    private Integer employee_id;
}

