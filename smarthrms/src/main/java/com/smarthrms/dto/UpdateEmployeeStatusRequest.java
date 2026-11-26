package com.smarthrms.dto;

import com.smarthrms.enums.EmployeeStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateEmployeeStatusRequest(

        @NotNull(message = "Employee status is required")
        EmployeeStatus status

) {}
