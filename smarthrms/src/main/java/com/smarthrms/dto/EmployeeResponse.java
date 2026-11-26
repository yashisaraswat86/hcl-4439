package com.smarthrms.dto;

import com.smarthrms.enums.EmployeeStatus;
import java.time.LocalDate;

public record EmployeeResponse(
        Long id,
        String employeeCode,
        Long candidateId,
        String candidateName,
        String departmentName,
        String departmentCode,
        String designation,
        LocalDate joiningDate,
        EmployeeStatus status
) {}
