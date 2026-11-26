package com.smarthrms.service;

import com.smarthrms.entity.Application;
import com.smarthrms.entity.Interview;
import com.smarthrms.enums.InterviewStatus;
import com.smarthrms.repository.ApplicationRepository;
import com.smarthrms.repository.InterviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;

    public InterviewService(
            InterviewRepository interviewRepository,
            ApplicationRepository applicationRepository) {
        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
    }

    @Transactional
    public Interview scheduleInterview(
            Long applicationId,
            LocalDateTime scheduledAt,
            Integer durationMinutes,
            String meetingLink,
            String email,
            boolean isAdmin) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Application not found"));

        if (!isAdmin &&
                !application.getJob().getRecruiter().getEmail().equals(email)) {
            throw new AccessDeniedException(
                    "You are not authorized to schedule an interview for this application");
        }

        if (scheduledAt == null || !scheduledAt.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Interview must be scheduled for a future date and time");
        }

        if (durationMinutes == null || durationMinutes <= 0) {
            throw new IllegalArgumentException(
                    "Interview duration must be greater than zero");
        }

        Interview interview = new Interview();

        interview.setApplication(application);
        interview.setScheduledAt(scheduledAt);
        interview.setDurationMinutes(durationMinutes);
        interview.setMeetingLink(meetingLink);

        return interviewRepository.save(interview);
    }

    public List<Interview> getInterviewsByApplication(
            Long applicationId,
            String email,
            boolean isAdmin,
            boolean isRecruiter) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Application not found"));

        if (!isAdmin) {
            boolean hasAccess;

            if (isRecruiter) {
                hasAccess = application.getJob().getRecruiter()
                        .getEmail().equals(email);
            } else {
                hasAccess = application.getCandidate().getUser()
                        .getEmail().equals(email);
            }

            if (!hasAccess) {
                throw new AccessDeniedException(
                        "You are not authorized to view these interviews");
            }
        }

        return interviewRepository.findByApplication_Id(applicationId);
    }

    @Transactional
    public Interview updateInterviewStatus(
            Long interviewId,
            InterviewStatus newStatus,
            String email,
            boolean isAdmin) {

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Interview not found"));

        if (!isAdmin &&
                !interview.getApplication().getJob().getRecruiter()
                        .getEmail().equals(email)) {
            throw new AccessDeniedException(
                    "You are not authorized to update this interview");
        }

        InterviewStatus currentStatus = interview.getStatus();

        if (currentStatus == InterviewStatus.COMPLETED ||
                currentStatus == InterviewStatus.CANCELLED) {
            throw new IllegalArgumentException(
                    "Cannot update a completed or cancelled interview");
        }

        if (currentStatus == newStatus) {
            return interview;
        }

        interview.setStatus(newStatus);

        return interviewRepository.save(interview);
    }
}