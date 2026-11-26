
package com.smarthrms.controller;

import com.smarthrms.dto.EmployeeOnboardingRequest;
import com.smarthrms.dto.EmployeeResponse;
import com.smarthrms.dto.UpdateEmployeeStatusRequest;
import com.smarthrms.entity.Employee;
import com.smarthrms.enums.EmployeeStatus;
import com.smarthrms.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Onboard employee
    @PostMapping("/onboard/{offerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse onboardEmployee(
            @PathVariable Long offerId,
            @Valid @RequestBody EmployeeOnboardingRequest request) {

        Employee employee = employeeService.onboardEmployee(
                offerId,
                request.departmentId(),
                request.designation()
        );

        return convertToResponse(employee);
    }

    // Get all employees
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Get employee by ID
    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployeeById(
            @PathVariable Long employeeId) {

        return convertToResponse(
                employeeService.getEmployeeById(employeeId)
        );
    }

    // Get employee by employee code
    @GetMapping("/code/{employeeCode}")
    public EmployeeResponse getEmployeeByCode(
            @PathVariable String employeeCode) {

        return convertToResponse(
                employeeService.getEmployeeByCode(employeeCode)
        );
    }

    // Get employees by status
    @GetMapping("/status/{status}")
    public List<EmployeeResponse> getEmployeesByStatus(
            @PathVariable EmployeeStatus status) {

        return employeeService.getEmployeesByStatus(status)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Update employee status
    @PatchMapping("/{employeeId}/status")
    public EmployeeResponse updateEmployeeStatus(
            @PathVariable Long employeeId,
            @Valid @RequestBody UpdateEmployeeStatusRequest request) {

        Employee employee = employeeService.updateEmployeeStatus(
                employeeId,
                request.status()
        );

        return convertToResponse(employee);
    }

    // Convert Employee entity into response DTO
    private EmployeeResponse convertToResponse(Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getCandidate().getId(),
                employee.getCandidate().getUser().getName(),
                employee.getDepartment().getName(),
                employee.getDepartment().getCode(),
                employee.getDesignation(),
                employee.getJoiningDate(),
                employee.getStatus()
        );
    }

    @GetMapping("/department/{departmentId}")
    public List<EmployeeResponse> getEmployeesByDepartment(
            @PathVariable Long departmentId) {

        return employeeService.getEmployeesByDepartment(departmentId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/department/{departmentId}/count")
    public long getEmployeeCountByDepartment(
            @PathVariable Long departmentId) {

        return employeeService.getEmployeeCountByDepartment(departmentId);
    }
}
