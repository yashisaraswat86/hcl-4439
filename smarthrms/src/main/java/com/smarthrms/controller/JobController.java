package com.smarthrms.controller;

import com.smarthrms.entity.Job;
import com.smarthrms.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(
            @RequestBody Job job,
            @RequestParam Long recruiterId) {

        Job createdJob = jobService.createJob(job, recruiterId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(jobService.getJobById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/published")
    public ResponseEntity<List<Job>> getPublishedJobs() {
        return ResponseEntity.ok(jobService.getPublishedJobs());
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<Job> publishJob(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(jobService.publishJob(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
