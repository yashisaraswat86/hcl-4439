package com.abes.hcldatabase.controller;

import com.abes.hcldatabase.model.Employee;
import com.abes.hcldatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/v1/users")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String message() {
        return "Welcome";
    }

    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/addBulk")
    public String addInBulk(@RequestBody List<Employee> employees) {
        return employeeService.addEmployeeBulk(employees);
    }

    @PostMapping("/login")
    public String login(@RequestBody Employee employee) {
        return employeeService.verification(employee);
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmp();
    }

    @GetMapping("/get/{id}")
    public Employee getEmpById(@PathVariable("id") Long empId) {
        return employeeService.getEmpById(empId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmpById(@PathVariable("id") Long empId) {
        return employeeService.deleteById(empId);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmpById(
            @PathVariable("id") Long empId,
            @RequestBody Employee employee) {
        return employeeService.updateById(empId, employee);
    }
}
