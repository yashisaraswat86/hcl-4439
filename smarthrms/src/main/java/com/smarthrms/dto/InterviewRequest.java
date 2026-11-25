package com.smarthrms.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record InterviewRequest(

        @NotNull(message = "Interview date and time is required")
        @Future(message = "Interview must be scheduled in the future")
        LocalDateTime scheduledAt,

        @NotNull(message = "Duration is required")
        @Positive(message = "Duration must be greater than zero")
        Integer durationMinutes,

        String meetingLink
) {
}
