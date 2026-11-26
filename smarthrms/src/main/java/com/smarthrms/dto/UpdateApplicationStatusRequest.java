package com.smarthrms.dto;

import com.smarthrms.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateApplicationStatusRequest(

        @NotNull(message = "Application status is required")
        ApplicationStatus status

) {
}
