package com.evoter.vote.repository;

import com.evoter.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author showunmioludotun
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
