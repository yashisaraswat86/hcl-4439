package com.smarthrms.controller;

import com.smarthrms.dto.ApplicationResponse;
import com.smarthrms.dto.UpdateApplicationStatusRequest;
import com.smarthrms.entity.Application;
import com.smarthrms.service.ApplicationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Application> applyToJob(
            @RequestParam Long candidateId,
            @RequestParam Long jobId) {

        Application application =
                applicationService.applyToJob(candidateId, jobId);

        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getCandidateApplications(
            @RequestParam Long candidateId) {

        return ResponseEntity.ok(
                applicationService.getCandidateApplications(candidateId)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable Long id,
            @RequestBody UpdateApplicationStatusRequest request) {

        return ResponseEntity.ok(
                applicationService.updateApplicationStatus(id, request.status())
        );
    }
}
