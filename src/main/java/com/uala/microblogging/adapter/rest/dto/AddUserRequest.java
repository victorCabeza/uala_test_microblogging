package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.User;

public record AddUserRequest(String id, String name, String lastName) {
    public User toUser() {
        return User.Builder.
                builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .build();
    }
}
