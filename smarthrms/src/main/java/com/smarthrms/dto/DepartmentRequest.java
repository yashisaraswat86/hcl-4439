package com.smarthrms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentRequest(

        @NotBlank(message = "Department name is required")
        @Size(max = 100)
        String name,

        @NotBlank(message = "Department code is required")
        @Size(max = 20)
        String code,

        @Size(max = 500)
        String description

) {}
