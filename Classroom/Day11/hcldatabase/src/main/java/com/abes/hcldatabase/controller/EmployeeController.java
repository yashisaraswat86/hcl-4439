package com.abes.hcldatabase.controller;

import com.abes.hcldatabase.model.Employee;
import com.abes.hcldatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService  employeeService ;
    @GetMapping("/")
    public String msg() {
        return "<h1>Welcome</h1>";
    }
    @GetMapping("/add")
    public String addemp(){
        return employeeService.createNewEmployee();
    }

    @GetMapping("/getemp/{empid}")
    public Employee getemp(@PathVariable  Long empid){
        return employeeService.getEmpById(empid);
    }

    @GetMapping("/addall")
    public String addAllEmp(){
        return employeeService.addAllEmp() ;
    }

    @GetMapping("/getall")
    public List<Employee> getAllEmp(){
        return employeeService.getAllEmp() ;
    }

    @DeleteMapping("/delete/{empid}")
    public String deleteById(@PathVariable  Long empid){
        return employeeService.deleteEmpById(empid) ;
    }

    @PutMapping("/update/{empid}")
    public String upadate(@PathVariable  Long empid){
        return employeeService.updateEmpById(empid) ;
    }
}
