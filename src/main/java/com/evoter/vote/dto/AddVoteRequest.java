package com.evoter.vote.dto;

public record AddVoteRequest(
        Long userId,
        Integer candidateId,
        Integer pollId
) {
}
