package com.smarthrms.repository;

import com.smarthrms.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByApplication_Id(Long applicationId);
}

