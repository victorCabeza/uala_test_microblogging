package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.User;

public record AddFollowingRequest(String following, String follower) {
    public User buildFollowingUser() {
        return User.Builder.builder().id(following).build();
    }

    public User buildFollowerUser() {
        return User.Builder.builder().id(follower).build();
    }
}
