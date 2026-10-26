package com.abes.hcldatabase.service;

import com.abes.hcldatabase.model.Employee;
import com.abes.hcldatabase.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo ;

    public String createNewEmployee(){
        Employee emp1 = new Employee(2001 , "Ram" , "Java" , "Manager" ) ;
        employeeRepo.save(emp1);
        return "Employee added" ;
    }

    public Employee getEmpById(Long empid){
        return employeeRepo.findById(empid).orElse(null);
    }

    public String addAllEmp(){
        List<Employee> employee = List.of(
                new Employee(2001 , "Ram" , "Java" , "Manager" ),
        new Employee(2002 , "Raj" , "Java" , "Manager" ),
        new Employee(2003 , "Rahul" , "Java" , "Manager" ),
        new Employee(2004 , "Diya" , "Java" , "Admin" )
        );
        employeeRepo.saveAll(employee);
        return "All employee added" ;
    }

    public List<Employee> getAllEmp(){
        return employeeRepo.findAll() ;
    }

    public String deleteEmpById(Long empid){
        employeeRepo.deleteById(empid);
        return "Employee Deleted" ;
    }

    public String updateEmpById(Long empid){
        if(employeeRepo.existsById(empid)){
            employeeRepo.deleteById(empid);
        }
        return "Employee Updated" ;
    }
}
