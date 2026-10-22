package com.example.hrms.controller;

import com.example.hrms.model.DemoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Specific component
public class EmployeeController {
    @Autowired
    private DemoEmployee demoEmployee ;

    @GetMapping("/")
    public String welcome(){
        return "<h1 style = 'color : purple '>WELCOME TO HRMS APP<h1>" ;
    }
    @GetMapping("/add")
    public int sum(){
        int res  = demoEmployee.addon();
        return res ;
    }

    @GetMapping("/sqrt")
    public double getsqrt(){
        return demoEmployee.squareroot();
    }

    @GetMapping("/display")
    public String display(){
        return demoEmployee.show();
    }

    @GetMapping("/greater")
    public int greaterno(){
        return demoEmployee.greatre();
    }
}
