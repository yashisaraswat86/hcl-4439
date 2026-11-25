package com.smarthrms.dto;

import java.time.LocalDateTime;

public record ApplicationResponse(
        Long applicationId,
        Long jobId,
        String jobTitle,
        String status,
        LocalDateTime appliedAt
) {
}
