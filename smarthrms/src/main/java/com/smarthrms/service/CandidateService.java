package com.smarthrms.service;

import com.smarthrms.entity.Candidate;
import com.smarthrms.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }
}