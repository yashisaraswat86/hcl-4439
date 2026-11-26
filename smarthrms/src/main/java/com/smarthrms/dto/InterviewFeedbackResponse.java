package com.smarthrms.dto;

import java.time.LocalDateTime;

public record InterviewFeedbackResponse(
        Long id,
        Long interviewId,
        Long interviewerId,
        String interviewerName,
        Integer technicalRating,
        Integer communicationRating,
        String strengths,
        String improvementAreas,
        String comments,
        String recommendation,
        LocalDateTime submittedAt
) {}
