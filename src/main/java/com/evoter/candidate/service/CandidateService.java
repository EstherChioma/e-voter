package com.evoter.candidate.service;

import com.evoter.candidate.model.Candidate;
import com.evoter.candidate.repository.CandidateRepository;
import com.evoter.candidate.dto.AddCandidateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author showunmioludotun
 */
@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate addCandidate(AddCandidateRequest request) {
        Candidate candidate = new Candidate();
        candidate.setPartyId(request.partyId());
        candidate.setPollTypeId(request.pollTypeId());
        candidate.setName(request.name());
        candidate.setAge(request.age());
        candidate.setGender(request.gender());
        candidate.setVoteCount(0L);
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public void deleteCandidateById(Long id) {
        candidateRepository.deleteById(id);
    }

    public List<Candidate> getCandidatesByPollTypeId(Integer pollTypeId) {
        List<Candidate> candidates = candidateRepository.findByPollTypeId(pollTypeId);
        return candidates;
    }
}
