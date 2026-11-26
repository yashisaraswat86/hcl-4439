package com.smarthrms.service;

import com.smarthrms.dto.ApplicationResponse;
import com.smarthrms.entity.Application;
import com.smarthrms.entity.Candidate;
import com.smarthrms.entity.Job;
import com.smarthrms.enums.ApplicationStatus;
import com.smarthrms.repository.ApplicationRepository;
import com.smarthrms.repository.CandidateRepository;
import com.smarthrms.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
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
    public Application applyToJob(String email, Long jobId) {
        Candidate candidate = candidateRepository.findByUser_Email(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Candidate profile not found"));

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

    public List<ApplicationResponse> getCandidateApplications(String email) {
        Candidate candidate = candidateRepository.findByUser_Email(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Candidate profile not found"));

        Long candidateId = candidate.getId();

        return applicationRepository.findByCandidate_Id(candidateId)
                .stream()
                .map(application -> new ApplicationResponse(
                        application.getId(),
                        application.getJob().getId(),
                        application.getJob().getTitle(),
                        application.getStatus().name(),
                        application.getAppliedAt()
                ))
                .toList();
    }

    @Transactional
    public ApplicationResponse updateApplicationStatus(
            Long applicationId,
            ApplicationStatus newStatus,
            String email,
            boolean isAdmin) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Application not found"));

        if (!isAdmin &&
                !application.getJob().getRecruiter().getEmail().equals(email)) {
            throw new AccessDeniedException(
                    "You are not authorized to update this application");
        }

        ApplicationStatus currentStatus = application.getStatus();

        boolean validTransition = switch (currentStatus) {

            case APPLIED ->
                    newStatus == ApplicationStatus.SHORTLISTED ||
                            newStatus == ApplicationStatus.REJECTED ||
                            newStatus == ApplicationStatus.WITHDRAWN;

            case SHORTLISTED ->
                    newStatus == ApplicationStatus.INTERVIEW ||
                            newStatus == ApplicationStatus.REJECTED ||
                            newStatus == ApplicationStatus.WITHDRAWN;

            case INTERVIEW ->
                    newStatus == ApplicationStatus.SELECTED ||
                            newStatus == ApplicationStatus.REJECTED ||
                            newStatus == ApplicationStatus.WITHDRAWN;

            case SELECTED ->
                    newStatus == ApplicationStatus.HIRED;

            case REJECTED, WITHDRAWN, HIRED -> false;
        };

        if (!validTransition) {
            throw new IllegalArgumentException(
                    "Invalid status transition: " +
                            currentStatus + " -> " + newStatus
            );
        }

        application.setStatus(newStatus);

        return new ApplicationResponse(
                application.getId(),
                application.getJob().getId(),
                application.getJob().getTitle(),
                application.getStatus().name(),
                application.getAppliedAt()
        );
    }
}