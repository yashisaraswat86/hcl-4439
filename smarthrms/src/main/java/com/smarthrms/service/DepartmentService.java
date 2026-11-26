package com.smarthrms.service;

import com.smarthrms.entity.Department;
import com.smarthrms.repository.DepartmentRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(
            String name,
            String code,
            String description) {

        if (departmentRepository.existsByNameIgnoreCase(name)) {
            throw new IllegalArgumentException(
                    "Department name already exists");
        }

        if (departmentRepository.existsByCodeIgnoreCase(code)) {
            throw new IllegalArgumentException(
                    "Department code already exists");
        }

        Department department = new Department();
        department.setName(name.trim());
        department.setCode(code.trim().toUpperCase());
        department.setDescription(description);
        department.setActive(true);

        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Department not found"));
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Department> getActiveDepartments() {
        return departmentRepository.findByActive(true);
    }

    @Transactional
    public Department updateDepartment(
            Long id,
            String name,
            String code,
            String description) {

        Department department = getDepartmentById(id);

        if (!department.getName().equalsIgnoreCase(name.trim())
                && departmentRepository.existsByNameIgnoreCase(name)) {
            throw new IllegalArgumentException(
                    "Department name already exists");
        }

        if (!department.getCode().equalsIgnoreCase(code.trim())
                && departmentRepository.existsByCodeIgnoreCase(code)) {
            throw new IllegalArgumentException(
                    "Department code already exists");
        }

        department.setName(name.trim());
        department.setCode(code.trim().toUpperCase());
        department.setDescription(description);

        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartmentStatus(Long id, boolean active) {
        Department department = getDepartmentById(id);
        department.setActive(active);
        return departmentRepository.save(department);
    }
}
