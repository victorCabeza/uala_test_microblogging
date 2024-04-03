package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.Message;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetTimelineResponse(UUID uuid, String text, LocalDateTime creationDate, String userId) {
    public static GetTimelineResponse from(final Message message) {
        return new GetTimelineResponse(message.getUuid(), message.getText(), message.getCreationDate(), message.getCreatedBy().id());
    }
}
