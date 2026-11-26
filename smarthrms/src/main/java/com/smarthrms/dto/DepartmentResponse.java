package com.smarthrms.dto;

import java.time.LocalDateTime;

public record DepartmentResponse(
        Long id,
        String name,
        String code,
        String description,
        boolean active,
        LocalDateTime createdAt
) {}
