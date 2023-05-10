package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("/")
public class ReportController {

 private final ReportService service;
    
    public ReportController(ReportService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public String getMypage( @AuthenticationPrincipal UserDetail user,Model model) {
        
        model.addAttribute("reportmylist", service.getMyList(user.getEmployee()));
        System.out.println("test");
        return "report/toppage";
    }
    
    @GetMapping("/report/list")
    public String getList(Model model) {
        
        model.addAttribute("reportlist", service.getReportList());
        
        
        return "report/list";
    }
    
    @GetMapping("/report/detail/{id}/")
    public String getDetail(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetails user, Model model) {
        
        model.addAttribute("report", service.getReport(id));
        model.addAttribute("user", user);
        
        
        return "report/detail";
    }
    
    @GetMapping("/report/register")
    public String getRegister(@ModelAttribute Report report, @AuthenticationPrincipal UserDetails user, Model model) {
//        employeeの今ログインしているユーザー情報の取得
//        このUserDetailクラスは、フィールドとして、Employee型のemployeeを定義しています。→UserDetail.java
        model.addAttribute("user", user);
        
//        String code = user.getUsername();
//        System.out.println("name is " + code);
        return "report/register";
    }
    
    @PostMapping("/report/register")
    public String postRegister(@Validated Report report,  @AuthenticationPrincipal UserDetail user, BindingResult res) {
        try {
          //  @AuthenticationPrincipal UserDetails user
//インターフェイスがUserDetailだった場合            
//            if(user instanceof UserDetail) {
//                UserDetail user2 = (UserDetail)user;
//            }
                
            
//      UserDetail user2 = (UserDetail)user;
        
        report.setEmployee(user.getEmployee());
            
            
        service.saveReport(report);
        
        return "redirect:/report/list";
        
        } catch(Exception e) {
            System.out.println("re");
            return  "/report/Recode";
        }
    }
    
    @GetMapping("/report/update/{id}/")
    public String getreport(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetails user, Model model) {
        
        model.addAttribute("report", service.getReport(id));
        model.addAttribute("user", user);
        return "report/update";
    }
    
    @PostMapping("/report/update/{id}/")
    public String postreport(@PathVariable("id") Integer id, Model model, Report report) {
       
//        Report currentemp =  service.getReport(id);
        
//        if(report.getName() == "") {
//            report.setName(currentemp.getName());
//          } 
//
//        if(report.getAuthentication().getPassword() == "") {
//            report.getAuthentication().setPassword(currentemp.getAuthentication().getPassword());
//          } 
       
        service.saveReport(report);
        return "redirect:/report/list";
    }
}
