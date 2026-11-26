package com.smarthrms.controller;

import com.smarthrms.dto.DepartmentRequest;
import com.smarthrms.dto.DepartmentResponse;
import com.smarthrms.entity.Department;
import com.smarthrms.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse createDepartment(
            @Valid @RequestBody DepartmentRequest request) {

        Department department = departmentService.createDepartment(
                request.name(),
                request.code(),
                request.description()
        );

        return convertToResponse(department);
    }

    @GetMapping
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/active")
    public List<DepartmentResponse> getActiveDepartments() {
        return departmentService.getActiveDepartments()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public DepartmentResponse getDepartmentById(
            @PathVariable Long id) {

        return convertToResponse(
                departmentService.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {

        Department department = departmentService.updateDepartment(
                id,
                request.name(),
                request.code(),
                request.description()
        );

        return convertToResponse(department);
    }

    @PatchMapping("/{id}/status")
    public DepartmentResponse updateDepartmentStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return convertToResponse(
                departmentService.updateDepartmentStatus(id, active));
    }

    private DepartmentResponse convertToResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getCode(),
                department.getDescription(),
                department.isActive(),
                department.getCreatedAt()
        );
    }
}
