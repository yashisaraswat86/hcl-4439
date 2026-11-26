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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public ResponseEntity<InterviewResponse> scheduleInterview(
            @RequestParam Long applicationId,
            @Valid @RequestBody InterviewRequest request,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        Interview interview = interviewService.scheduleInterview(
                applicationId,
                request.scheduledAt(),
                request.durationMinutes(),
                request.meetingLink(),
                authentication.getName(),
                isAdmin
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponse(interview));
    }

    @GetMapping("/application/{applicationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER', 'CANDIDATE')")
    public ResponseEntity<List<InterviewResponse>> getInterviews(
            @PathVariable Long applicationId,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean isRecruiter = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_RECRUITER"));

        List<InterviewResponse> interviews =
                interviewService.getInterviewsByApplication(
                                applicationId,
                                authentication.getName(),
                                isAdmin,
                                isRecruiter
                        ).stream()
                        .map(this::convertToResponse)
                        .toList();

        return ResponseEntity.ok(interviews);
    }

    @PatchMapping("/{interviewId}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public ResponseEntity<InterviewResponse> updateInterviewStatus(
            @PathVariable Long interviewId,
            @Valid @RequestBody UpdateInterviewStatusRequest request,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        Interview interview = interviewService.updateInterviewStatus(
                interviewId,
                request.status(),
                authentication.getName(),
                isAdmin
        );

        return ResponseEntity.ok(convertToResponse(interview));
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
}