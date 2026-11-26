package com.smarthrms.controller;

import com.smarthrms.dto.JobResponse;
import com.smarthrms.entity.Job;
import com.smarthrms.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Only ADMIN and RECRUITER can create jobs
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @RequestBody Job job,
            @RequestParam Long recruiterId) {

        JobResponse createdJob =
                jobService.createJob(job, recruiterId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdJob);
    }

    // Anyone can view an individual job
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    jobService.getJobById(id)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Only ADMIN and RECRUITER can view all jobs
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {

        return ResponseEntity.ok(
                jobService.getAllJobs()
        );
    }

    // Anyone can view published jobs
    @PreAuthorize("permitAll()")
    @GetMapping("/published")
    public ResponseEntity<List<JobResponse>> getPublishedJobs() {

        return ResponseEntity.ok(
                jobService.getPublishedJobs()
        );
    }

    // Only ADMIN and RECRUITER can publish jobs
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    @PatchMapping("/{id}/publish")
    public ResponseEntity<JobResponse> publishJob(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    jobService.publishJob(id)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

