package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    public static enum Role {
        一般, 管理者
    }
    
    @Id
    @Column(length = 20, nullable = false)
    private String code;
    
    @Column(length = 255, nullable = false)
    private String password;
    
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
//   　なぜ失敗するのか不明？？
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;
}

