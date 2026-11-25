package com.smarthrms.service;

import com.smarthrms.dto.ApplicationResponse;
import com.smarthrms.entity.Application;
import com.smarthrms.entity.Candidate;
import com.smarthrms.entity.Job;
import com.smarthrms.repository.ApplicationRepository;
import com.smarthrms.repository.CandidateRepository;
import com.smarthrms.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            CandidateRepository candidateRepository,
            JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
    }

    @Transactional
    public Application applyToJob(Long candidateId, Long jobId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Candidate not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job not found"));

        if (!"PUBLISHED".equals(job.getStatus())) {
            throw new IllegalStateException("Job is not published");
        }

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);

        return applicationRepository.save(application);
    }

    public List<ApplicationResponse> getCandidateApplications(Long candidateId) {
        if (!candidateRepository.existsById(candidateId)) {
            throw new EntityNotFoundException("Candidate not found");
        }

        return applicationRepository.findByCandidate_Id(candidateId)
                .stream()
                .map(application -> new ApplicationResponse(
                        application.getId(),
                        application.getJob().getId(),
                        application.getJob().getTitle(),
                        application.getStatus(),
                        application.getAppliedAt()
                ))
                .toList();
    }

    @Transactional
    public ApplicationResponse updateApplicationStatus(Long applicationId, String status) {
        if (!List.of("SHORTLISTED", "REJECTED", "HIRED").contains(status)) {
            throw new IllegalArgumentException("Invalid application status");
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Application not found"));

        application.setStatus(status);

        return new ApplicationResponse(
                application.getId(),
                application.getJob().getId(),
                application.getJob().getTitle(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }
}
