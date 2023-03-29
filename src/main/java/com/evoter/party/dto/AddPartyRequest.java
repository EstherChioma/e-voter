package com.evoter.party.dto;

public record AddPartyRequest(
        String name,
        String acronym,
        String logo
) {
}
