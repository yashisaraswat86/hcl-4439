
package com.smarthrms.service;

import com.smarthrms.entity.Department;
import com.smarthrms.entity.Employee;
import com.smarthrms.entity.Offer;
import com.smarthrms.enums.EmployeeStatus;
import com.smarthrms.enums.OfferStatus;
import com.smarthrms.repository.DepartmentRepository;
import com.smarthrms.repository.EmployeeRepository;
import com.smarthrms.repository.OfferRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OfferRepository offerRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            OfferRepository offerRepository,
            DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.offerRepository = offerRepository;
        this.departmentRepository = departmentRepository;
    }

    // Onboard an employee using an accepted offer
    @Transactional
    public Employee onboardEmployee(
            Long offerId,
            Long departmentId,
            String designation) {

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Offer not found"));

        // Only accepted offers can be onboarded
        if (offer.getStatus() != OfferStatus.ACCEPTED) {
            throw new IllegalArgumentException(
                    "Only candidates with accepted offers can be onboarded");
        }

        // Retrieve the candidate through the application
        var candidate = offer.getApplication().getCandidate();

        // Prevent duplicate onboarding
        if (employeeRepository.existsByCandidate_Id(candidate.getId())) {
            throw new IllegalArgumentException(
                    "Candidate is already onboarded");
        }

        // Validate designation
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException(
                    "Designation is required");
        }

        // Retrieve the department
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Department not found"));

        // Prevent assignment to inactive departments
        if (!department.isActive()) {
            throw new IllegalArgumentException(
                    "Cannot assign employee to an inactive department");
        }

        Employee employee = new Employee();

        employee.setCandidate(candidate);

        employee.setEmployeeCode(
                "EMP-" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase()
        );

        employee.setDepartment(department);
        employee.setDesignation(designation.trim());
        employee.setJoiningDate(offer.getJoiningDate());
        employee.setStatus(EmployeeStatus.ONBOARDING);

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Employee not found"));
    }

    public Employee getEmployeeByCode(String employeeCode) {
        return employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() ->
                        new EntityNotFoundException("Employee not found"));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByStatus(EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }

    // Update employee lifecycle status
    @Transactional
    public Employee updateEmployeeStatus(
            Long employeeId,
            EmployeeStatus newStatus) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Employee not found"));

        if (newStatus == null) {
            throw new IllegalArgumentException(
                    "Employee status is required");
        }

        EmployeeStatus currentStatus = employee.getStatus();

        boolean validTransition = switch (currentStatus) {
            case ONBOARDING -> newStatus == EmployeeStatus.ACTIVE;

            case ACTIVE -> newStatus == EmployeeStatus.INACTIVE
                    || newStatus == EmployeeStatus.TERMINATED;

            case INACTIVE -> newStatus == EmployeeStatus.ACTIVE
                    || newStatus == EmployeeStatus.TERMINATED;

            case TERMINATED -> false;
        };

        if (!validTransition) {
            throw new IllegalArgumentException(
                    "Cannot change employee status from "
                            + currentStatus + " to " + newStatus);
        }

        employee.setStatus(newStatus);

        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(Long departmentId) {

        if (!departmentRepository.existsById(departmentId)) {
            throw new EntityNotFoundException("Department not found");
        }

        return employeeRepository.findByDepartment_Id(departmentId);
    }

    @Transactional(readOnly = true)
    public long getEmployeeCountByDepartment(Long departmentId) {

        if (!departmentRepository.existsById(departmentId)) {
            throw new EntityNotFoundException("Department not found");
        }

        return employeeRepository.countByDepartment_Id(departmentId);
    }
}
