package com.smarthrms.dto;

import java.time.LocalDateTime;

public record InterviewResponse(
        Long interviewId,
        Long applicationId,
        LocalDateTime scheduledAt,
        Integer durationMinutes,
        String meetingLink,
        String status
) {
}
