package com.smarthrms.dto;

import com.smarthrms.enums.InterviewStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateInterviewStatusRequest(

        @NotNull(message = "Interview status is required")
        InterviewStatus status

) {
}
