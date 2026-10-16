package com.example.employeesalaryapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Address address() {
        return new Address();
    }

    @Bean
    public Department dept() {
        return new Department(101,  "IT" , "Diya");
    }

    @Bean
    public Salary salary() {
        return new Salary(30000, 10000);
    }

    @Bean
    public Employee employee(Address address, Department dept, Salary salary) {
        return new Employee(address, dept, salary);
    }
}
