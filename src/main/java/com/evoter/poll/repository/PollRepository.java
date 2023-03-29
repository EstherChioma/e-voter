package com.evoter.poll.repository;

import com.evoter.poll.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author showunmioludotun
 */
public interface PollRepository extends JpaRepository<Poll, Long> {
//    @Query("SELECT p FROM Poll p JOIN FETCH p.candidates c JOIN FETCH p.pollType")
//    List<Poll> findAllWithCandidatesAndPollType();
}
