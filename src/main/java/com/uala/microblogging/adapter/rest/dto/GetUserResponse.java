package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public record GetUserResponse(String id, String name, String lastName, Set<GetUserResponse> following) {
    public static GetUserResponse from(final User user) {
        return new GetUserResponse(user.id(), user.name(), user.lastName(), buildFollowing(user.following()));
    }

    private static Set<GetUserResponse> buildFollowing(final Set<User> followingUsers) {
        return followingUsers.stream().map(GetUserResponse::fromWithoutFollowers).collect(Collectors.toSet());
    }

    private static GetUserResponse fromWithoutFollowers(final User user) {
        return new GetUserResponse(user.id(), user.name(), user.lastName(), Set.of());
    }
}
