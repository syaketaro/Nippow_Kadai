package com.techacademy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
//import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;
    
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    
    @GetMapping("/list")
    public String getList(Model model) {
        
        model.addAttribute("employeelist", service.getEmployeeList());
        
        return "employee/list";
    }
    
    @GetMapping("/detail/{id}/")
    public String getDetail(@PathVariable("id") Integer id, Model model) {
        
        model.addAttribute("employee", service.getEmployee(id));
        
        return "employee/detail";
    }
    
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        return "employee/register";
    }
    
      //setterを利用して設定とは？？？
//    public void setDeleteFlag(Employee employee) {
//        
//        employee.setDeleteFlag(0);
//    }
    @PostMapping("/register")
    public String postRegister(Employee employee) {
    
        //ここの書き方あってる？？？　and 全くわからなポイント
        employee.setDeleteFlag(0);
        
        //employee.setEmployeeId();
        
        service.saveEmployee(employee);
        
        return "redirect:/employee/list";
    }
    
   
    
    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        
        model.addAttribute("employee", service.getEmployee(id));

        return "employee/update";
    }

    @PostMapping("/update/{id}/")
    public String postEmployee(Employee employee) {
        
        employee.setDeleteFlag(0);
        service.saveEmployee(employee);
       
        return "redirect:/employee/list";
    }
    
    @PostMapping("/delete/{id}/")
    //path="/update", params=
    public String delete(@PathVariable("id") Integer id) {
        //, Employee employee
        System.out.println("削除のメソッド開始");
        
        Employee employee = service.getEmployee(id);
        employee.setDeleteFlag(1);
        service.saveEmployee(employee);
       

        return "redirect:/employee/list";
    }
    
//    前の書き方上の書き方との比較
//    @PostMapping("/delete/{id}/")
//    //path="/update", params=
//    public String delete(Employee employee) {
//        System.out.println("削除のメソッド開始");
//        
//        
//        employee.setDeleteFlag(1);
//        service.saveEmployee(employee);
//       
//
//        return "redirect:/employee/list";
//    }
}
