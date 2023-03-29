package com.evoter.candidate.repository;

import com.evoter.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author showunmioludotun
 */
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByPollTypeId(Integer pollTypeId);
}
