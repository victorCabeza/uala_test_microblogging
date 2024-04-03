package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.Message;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetFollowingMessageResponse(UUID uuid, String text, LocalDateTime creationDate, String userId) {
    public static GetFollowingMessageResponse from(final Message message) {
        return new GetFollowingMessageResponse(message.getUuid(), message.getText(), message.getCreationDate(), message.getCreatedBy().id());
    }
}
