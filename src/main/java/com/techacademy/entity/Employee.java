package com.techacademy.entity;


import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
public class Employee {

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String name;
    
    
//    ここ、カリキュラどこに載ってる？？？
    @Column(nullable = false, updatable = false, insertable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
    
    @Column(nullable = false, updatable = false, insertable = false, 
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;
    
//    ここどうやって数値デフォルト０で設定するのか？
//    @Column(nullable = true)
//    private Integer deleteFlag;
//    @Column(nullable = true, columnDefinition = "tinyint(1) default 0")
//    private boolean deleteFlag;
    
    
    
    @Column(nullable = false)
    private Integer deleteFlag;
    
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;
    
    @PreRemove
    @Transactional
    private void preRemove() {
        if (authentication!=null) {
            authentication.setEmployee(null);
        }
    }
    
}
