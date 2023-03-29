package com.evoter.poll.dto;

import java.util.Date;

public record AddPollRequest(
        Integer pollTypeId,
        Date pollDate
) {
}
