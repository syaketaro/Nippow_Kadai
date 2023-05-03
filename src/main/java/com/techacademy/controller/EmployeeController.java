package com.techacademy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.techacademy.entity.Authentication;
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
    public String postRegister(@Validated Employee employee, BindingResult res) {
        try {
        if(res.hasErrors()) {
            return getRegister(employee);
        }
        //既に存在する社員番号の従業員を登録しようとするとエラーが発生してしまう
        // boolean existsByCode(String code);
//        Employee currentemp =  service.getEmployee(employee);
//        
//        if(employee.authentication.getCode() == currentemp.authentication.getCode()) {
//            return getRegister(employee);
//        }
        employee.setDeleteFlag(0);
        
        //employee.setEmployeeId();
        
        service.saveEmployee(employee);
        
        return "redirect:/employee/list";
        
        } catch(Exception e) {
            System.out.println("re");
            return  "/employee/Recode";
        }
    }
    
   
    
    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        
        model.addAttribute("employee", service.getEmployee(id));

        return "employee/update";
    }

    
//    1.更新前従業員インスタンスを取得する
//    2.更新前従業員インスタンスとブラウザからリクエストとして渡された従業員インスタンスを比較する
//    3.比較の結果、変更点があればテーブルへ反映、なければテーブルへ反映しない
//    4.画面遷移
    
    @PostMapping("/update/{id}/")
    public String postEmployee(@PathVariable("id") Integer id, Model model, Employee employee) {
//      1.更新前従業員インスタンスを取得する
        Employee currentemp =  service.getEmployee(id);
        //model.addAttribute("employee", service.getEmployee(id));
//      2.更新前従業員インスタンスとブラウザからリクエストとして渡された従業員インスタンスを比較する
        if(employee.getName() == "") {
            //名前の変更があったので更新
//          3.比較の結果、変更点があればテーブルへ反映、なければテーブルへ反映しない
            employee.setName(currentemp.getName());
          } 

        if(employee.getAuthentication().getPassword() == "") {
            //名前の変更があったので更新
//          3.比較の結果、変更点があればテーブルへ反映、なければテーブルへ反映しない
            employee.getAuthentication().setPassword(currentemp.getAuthentication().getPassword());
          } 
          
       
        employee.setDeleteFlag(0);
        service.saveEmployee(employee);
//      4.画面遷移
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
