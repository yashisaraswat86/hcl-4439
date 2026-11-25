package com.smarthrms.service;

import com.smarthrms.entity.Interview;
import com.smarthrms.entity.InterviewFeedback;
import com.smarthrms.entity.User;
import com.smarthrms.enums.Recommendation;
import com.smarthrms.enums.Role;
import com.smarthrms.repository.InterviewFeedbackRepository;
import com.smarthrms.repository.InterviewRepository;
import com.smarthrms.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InterviewFeedbackService {

    private final InterviewFeedbackRepository feedbackRepository;
    private final InterviewRepository interviewRepository;
    private final UserRepository userRepository;

    public InterviewFeedbackService(
            InterviewFeedbackRepository feedbackRepository,
            InterviewRepository interviewRepository,
            UserRepository userRepository) {

        this.feedbackRepository = feedbackRepository;
        this.interviewRepository = interviewRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public InterviewFeedback submitFeedback(
            Long interviewId,
            Long interviewerId,
            Integer technicalRating,
            Integer communicationRating,
            String strengths,
            String improvementAreas,
            String comments,
            Recommendation recommendation) {

        // 1. Check whether the interview exists
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Interview not found"));

        // 2. Check whether the interviewer exists
        User interviewer = userRepository.findById(interviewerId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Interviewer not found"));

        // 3. Check the user's role
        if (interviewer.getRole() != Role.INTERVIEWER) {
            throw new IllegalArgumentException(
                    "Only interviewers can submit feedback");
        }

        // 4. Validate ratings
        if (technicalRating == null || technicalRating < 1 || technicalRating > 5) {
            throw new IllegalArgumentException(
                    "Technical rating must be between 1 and 5");
        }

        if (communicationRating == null ||
                communicationRating < 1 || communicationRating > 5) {
            throw new IllegalArgumentException(
                    "Communication rating must be between 1 and 5");
        }

        // 5. Validate recommendation
        if (recommendation == null) {
            throw new IllegalArgumentException(
                    "Recommendation is required");
        }

        // 6. Prevent duplicate feedback
        boolean alreadySubmitted =
                feedbackRepository.existsByInterview_IdAndInterviewer_Id(
                        interviewId, interviewerId);

        if (alreadySubmitted) {
            throw new IllegalStateException(
                    "You have already submitted feedback for this interview");
        }

        // 7. Create and save feedback
        InterviewFeedback feedback = new InterviewFeedback();

        feedback.setInterview(interview);
        feedback.setInterviewer(interviewer);
        feedback.setTechnicalRating(technicalRating);
        feedback.setCommunicationRating(communicationRating);
        feedback.setStrengths(strengths);
        feedback.setImprovementAreas(improvementAreas);
        feedback.setComments(comments);
        feedback.setRecommendation(recommendation);

        return feedbackRepository.save(feedback);
    }

    @Transactional(readOnly = true)
    public List<InterviewFeedback> getFeedbackByInterview(Long interviewId) {

        if (!interviewRepository.existsById(interviewId)) {
            throw new EntityNotFoundException("Interview not found");
        }

        return feedbackRepository
                .findByInterview_IdOrderBySubmittedAtDesc(interviewId);
    }
}
