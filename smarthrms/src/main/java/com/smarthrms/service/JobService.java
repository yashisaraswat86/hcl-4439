package com.smarthrms.service;

import com.smarthrms.entity.Job;
import com.smarthrms.enums.Role;
import com.smarthrms.entity.User;
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
    public Job createJob(Job job, Long recruiterId) {
        User recruiter = userRepository.findById(recruiterId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Recruiter not found"));

        if (recruiter.getRole() != Role.RECRUITER) {
            throw new IllegalArgumentException(
                    "The user is not a recruiter");
        }

        job.setRecruiter(recruiter);
        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job not found"));
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getPublishedJobs() {
        return jobRepository.findByStatus("PUBLISHED");
    }

    @Transactional
    public Job publishJob(Long id) {
        Job job = getJobById(id);
        job.setStatus("PUBLISHED");
        return jobRepository.save(job);
    }
}
