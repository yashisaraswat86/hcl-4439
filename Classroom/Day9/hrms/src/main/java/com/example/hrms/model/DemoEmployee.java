package com.example.hrms.model;

import com.example.hrms.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoEmployee {

    @Autowired
    private EmpRepo repo ;

    public int addon(){
        return repo.add(20,30);
    }

    public double squareroot(){
        return repo.squareroot(100)  ;
    }

    public String show(){
        return repo.empData();
    }

    public int greatre(){
        return repo.greater(50 ,80) ;
    }
}
