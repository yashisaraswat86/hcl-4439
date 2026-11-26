package com.smarthrms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record JobResponse(
        Long id,
        String title,
        String description,
        String location,
        String employmentType,
        BigDecimal salaryMin,
        BigDecimal salaryMax,
        String status,
        LocalDateTime createdAt,
        RecruiterSummaryResponse recruiter
) {}
