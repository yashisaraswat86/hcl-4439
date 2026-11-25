package com.smarthrms.dto;

import com.smarthrms.enums.Recommendation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SubmitFeedbackRequest(

        @NotNull
        @Min(1)
        @Max(5)
        Integer technicalRating,

        @NotNull
        @Min(1)
        @Max(5)
        Integer communicationRating,

        String strengths,

        String improvementAreas,

        String comments,

        @NotNull
        Recommendation recommendation

) {}
