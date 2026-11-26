package com.smarthrms.controller;

import com.smarthrms.dto.ApplicationResponse;
import com.smarthrms.dto.UpdateApplicationStatusRequest;
import com.smarthrms.entity.Application;
import com.smarthrms.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Application> applyToJob(
            Authentication authentication,
            @RequestParam Long jobId) {

        Application application =
                applicationService.applyToJob(authentication.getName(), jobId);

        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<ApplicationResponse>> getCandidateApplications(
            Authentication authentication) {

        return ResponseEntity.ok(
                applicationService.getCandidateApplications(authentication.getName())
        );
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateApplicationStatusRequest request,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN"));

        return ResponseEntity.ok(
                applicationService.updateApplicationStatus(
                        id,
                        request.status(),
                        authentication.getName(),
                        isAdmin
                )
        );
    }
}