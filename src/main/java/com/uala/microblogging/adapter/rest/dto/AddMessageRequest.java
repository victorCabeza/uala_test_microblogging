package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.Message;
import com.uala.microblogging.model.User;

import java.time.LocalDateTime;

public record AddMessageRequest(String text, String userId) {
    public Message toMessage() {
        return new Message(this.text, LocalDateTime.now(), User.Builder.builder().id(userId).build());
    }
}
