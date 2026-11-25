package com.smarthrms.controller;

import com.smarthrms.dto.InterviewRequest;
import com.smarthrms.dto.InterviewResponse;
import com.smarthrms.dto.UpdateInterviewStatusRequest;
import com.smarthrms.entity.Interview;
import com.smarthrms.service.InterviewService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public ResponseEntity<InterviewResponse> scheduleInterview(
            @RequestParam Long applicationId,
            @Valid @RequestBody InterviewRequest request) {

        Interview interview = interviewService.scheduleInterview(
                applicationId,
                request.scheduledAt(),
                request.durationMinutes(),
                request.meetingLink()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponse(interview));
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<InterviewResponse>> getInterviews(
            @PathVariable Long applicationId) {

        List<InterviewResponse> interviews =
                interviewService.getInterviewsByApplication(applicationId)
                        .stream()
                        .map(this::convertToResponse)
                        .toList();

        return ResponseEntity.ok(interviews);
    }

    private InterviewResponse convertToResponse(Interview interview) {

        return new InterviewResponse(
                interview.getId(),
                interview.getApplication().getId(),
                interview.getScheduledAt(),
                interview.getDurationMinutes(),
                interview.getMeetingLink(),
                interview.getStatus().name()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @PatchMapping("/{interviewId}/status")
    public ResponseEntity<InterviewResponse> updateInterviewStatus(
            @PathVariable Long interviewId,
            @Valid @RequestBody UpdateInterviewStatusRequest request) {

        Interview interview = interviewService.updateInterviewStatus(
                interviewId,
                request.status()
        );

        return ResponseEntity.ok(convertToResponse(interview));
    }
}
