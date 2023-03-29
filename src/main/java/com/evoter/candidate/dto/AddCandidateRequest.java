package com.evoter.candidate.dto;

public record AddCandidateRequest(
        Integer partyId,
        Integer pollTypeId,
        String name,
        Integer age,
        String gender
) {
}
