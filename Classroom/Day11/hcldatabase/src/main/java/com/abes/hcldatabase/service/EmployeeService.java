package com.abes.hcldatabase.service;

import com.abes.hcldatabase.model.Employee;
import com.abes.hcldatabase.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public String createEmployee(Employee employee) {
        employeeRepo.save(employee);
        return "Employee added successfully";
    }

    public String addEmployeeBulk(List<Employee> employees) {
        employeeRepo.saveAll(employees);
        return "All employees added successfully";
    }

    public List<Employee> getAllEmp() {
        return employeeRepo.findAll();
    }

    public Employee getEmpById(Long empId) {
        return employeeRepo.findById(empId).orElse(null);
    }

    public String deleteById(Long empId) {
        if (employeeRepo.existsById(empId)) {
            employeeRepo.deleteById(empId);
            return "Employee deleted successfully";
        }

        return "Employee not found with ID: " + empId;
    }

    public Employee updateById(Long empId, Employee e) {
        Employee existingEmployee = employeeRepo.findById(empId).orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setEmp_name(e.getEmp_name());
            existingEmployee.setDesignation(e.getDesignation());
            existingEmployee.setProgStack(e.getProgStack());
            existingEmployee.setEmail(e.getEmail());

            return employeeRepo.save(existingEmployee);
        }

        return null;
    }

    public String verification(Employee emp) {
        if (emp == null) {
            return "Employee details are required";
        }

        // 1. If employee ID is provided, verify by ID and match with email (if email is also provided)
        if (emp.getEmp_id() != null && emp.getEmp_id() != 0) {
            Optional<Employee> existingOpt = employeeRepo.findById(emp.getEmp_id());
            if (existingOpt.isEmpty()) {
                return "Employee not found with ID: " + emp.getEmp_id();
            }

            Employee existing = existingOpt.get();
            if (emp.getEmail() != null && !emp.getEmail().isBlank()) {
                if (existing.getEmail() != null && existing.getEmail().equalsIgnoreCase(emp.getEmail())) {
                    return "Verification successful";
                }
                return "Email does not match our records";
            }
            return "Verification successful";
        }

        // 2. If only email is provided, verify by email
        if (emp.getEmail() != null && !emp.getEmail().isBlank()) {
            Optional<Employee> existingOpt = employeeRepo.findByEmail(emp.getEmail());
            if (existingOpt.isPresent()) {
                return "Verification successful";
            }
            return "Employee not found with email: " + emp.getEmail();
        }

        return "Please provide employee ID and email for verification";
    }
}
