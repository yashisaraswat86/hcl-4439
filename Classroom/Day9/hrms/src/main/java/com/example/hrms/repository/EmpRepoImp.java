package com.example.hrms.repository;

import org.springframework.stereotype.Component;

@Component
public class EmpRepoImp implements EmpRepo {

    @Override
    public double squareroot(double d) {
        return Math.sqrt(d);
    }

    @Override
    public String empData() {
        return "Employee Data";
    }

    @Override
    public int add(int a , int b){
        return a + b ;
    }

    @Override
    public int greater(int a, int b) {
        if(a > b){
            return a ;
        }
        return b ;
    }
}
