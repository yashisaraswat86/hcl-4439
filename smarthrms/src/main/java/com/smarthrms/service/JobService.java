
package com.smarthrms.service;

import com.smarthrms.dto.JobResponse;
import com.smarthrms.dto.RecruiterSummaryResponse;
import com.smarthrms.entity.Job;
import com.smarthrms.entity.User;
import com.smarthrms.enums.Role;
import com.smarthrms.repository.JobRepository;
import com.smarthrms.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository,
                      UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public JobResponse createJob(Job job, Long recruiterId) {

        User recruiter = userRepository.findById(recruiterId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Recruiter not found"));

        if (recruiter.getRole() != Role.RECRUITER) {
            throw new IllegalArgumentException(
                    "The user is not a recruiter");
        }

        job.setRecruiter(recruiter);

        Job savedJob = jobRepository.save(job);

        return mapToResponse(savedJob);
    }

    @Transactional(readOnly = true)
    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job not found"));

        return mapToResponse(job);
    }

    @Transactional(readOnly = true)
    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<JobResponse> getPublishedJobs() {

        return jobRepository.findByStatus("PUBLISHED")
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public JobResponse publishJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job not found"));

        job.setStatus("PUBLISHED");

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);
    }

    private JobResponse mapToResponse(Job job) {

        User recruiter = job.getRecruiter();

        RecruiterSummaryResponse recruiterResponse =
                new RecruiterSummaryResponse(
                        recruiter.getId(),
                        recruiter.getName(),
                        recruiter.getEmail()
                );

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getEmploymentType(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getStatus(),
                job.getCreatedAt(),
                recruiterResponse
        );
    }
}
