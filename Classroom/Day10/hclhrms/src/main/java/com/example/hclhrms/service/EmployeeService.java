package com.example.hclhrms.service;

import com.example.hclhrms.entity.Employee;
import com.example.hclhrms.repository.EmployeeRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepoImp employeeRepoImp;

    public String addEmp(){
        Employee emp1 = new Employee(101 , "Raj" , "DSE" , "JAVA" , "raj@gmail.com") ;
        return employeeRepoImp.addEmp(emp1) ;
    }

    public List<Employee> getEmp(){
        return employeeRepoImp.getEmp();
    }

    public Employee getEmpById(){
            return employeeRepoImp.getEmpById(101);
    }

    public List<Employee> getAllmp() {
        return employeeRepoImp.addAllEmp();
    }
}
