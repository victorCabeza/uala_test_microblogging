package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.Message;
import com.uala.microblogging.model.User;

import java.time.LocalDateTime;

public record AddMessageRequest(String text, String userId) {
    public Message toMessage() {
        return Message.Builder.builder()
                .text(this.text)
                .creationDate(LocalDateTime.now())
                .createdBy(User.Builder.builder().id(userId).build())
                .build();
    }
}
