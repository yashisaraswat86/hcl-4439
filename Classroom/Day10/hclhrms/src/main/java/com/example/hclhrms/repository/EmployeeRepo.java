package com.example.hclhrms.repository;

import com.example.hclhrms.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo {
    String addEmp(Employee emp) ;
    List<Employee> getEmp();
    Employee getEmpById(int emp_id) ;
    String deleteById(int emp_id);
    String updateById(int emp_id) ;
    List<Employee> addAllEmp() ;

}
