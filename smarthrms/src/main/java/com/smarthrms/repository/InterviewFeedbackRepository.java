package com.smarthrms.repository;

import com.smarthrms.entity.InterviewFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterviewFeedbackRepository extends JpaRepository<InterviewFeedback, Long> {

    boolean existsByInterview_IdAndInterviewer_Id(Long interviewId, Long interviewerId);

    Optional<InterviewFeedback> findByInterview_IdAndInterviewer_Id(
            Long interviewId, Long interviewerId);

    List<InterviewFeedback> findByInterview_IdOrderBySubmittedAtDesc(Long interviewId);

    List<InterviewFeedback> findByInterviewer_IdOrderBySubmittedAtDesc(Long interviewerId);
}
