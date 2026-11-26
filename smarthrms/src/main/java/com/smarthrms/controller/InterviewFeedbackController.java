package com.smarthrms.controller;

import com.smarthrms.dto.InterviewFeedbackResponse;
import com.smarthrms.dto.SubmitFeedbackRequest;
import com.smarthrms.entity.InterviewFeedback;
import com.smarthrms.service.InterviewFeedbackService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview-feedback")
public class InterviewFeedbackController {

    private final InterviewFeedbackService feedbackService;

    public InterviewFeedbackController(
            InterviewFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/{interviewId}")
    public ResponseEntity<InterviewFeedbackResponse> submitFeedback(
            @PathVariable Long interviewId,
            @RequestParam Long interviewerId,
            @Valid @RequestBody SubmitFeedbackRequest request) {

        InterviewFeedback feedback = feedbackService.submitFeedback(
                interviewId,
                interviewerId,
                request.technicalRating(),
                request.communicationRating(),
                request.strengths(),
                request.improvementAreas(),
                request.comments(),
                request.recommendation()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponse(feedback));
    }

    @GetMapping("/interview/{interviewId}")
    public ResponseEntity<List<InterviewFeedbackResponse>> getFeedback(
            @PathVariable Long interviewId) {

        List<InterviewFeedbackResponse> responses =
                feedbackService.getFeedbackByInterview(interviewId)
                        .stream()
                        .map(this::convertToResponse)
                        .toList();

        return ResponseEntity.ok(responses);
    }

    private InterviewFeedbackResponse convertToResponse(
            InterviewFeedback feedback) {

        return new InterviewFeedbackResponse(
                feedback.getId(),
                feedback.getInterview().getId(),
                feedback.getInterviewer().getId(),
                feedback.getInterviewer().getName(),
                feedback.getTechnicalRating(),
                feedback.getCommunicationRating(),
                feedback.getStrengths(),
                feedback.getImprovementAreas(),
                feedback.getComments(),
                feedback.getRecommendation().name(),
                feedback.getSubmittedAt()
        );
    }
}
