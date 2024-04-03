package com.uala.microblogging.adapter.rest.dto;


import com.uala.microblogging.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddFollowingRequestTest {

    @Test
    void givenAddFollowingRequestWhenGetFollowingUserThenSuccess() {
        // given
        final AddFollowingRequest addFollowingRequest = getAddFollowingRequest();

        // when
        final User followingUser = addFollowingRequest.buildFollowingUser();

        // then
        assertEquals(addFollowingRequest.following(), followingUser.id());
    }

    @Test
    void givenAddFollowingRequestWhenGetFollowerUserThenSuccess() {
        // given
        final AddFollowingRequest addFollowingRequest = getAddFollowingRequest();

        // when
        final User followerUser = addFollowingRequest.buildFollowerUser();

        // then
        assertEquals(addFollowingRequest.follower(), followerUser.id());
    }

    private AddFollowingRequest getAddFollowingRequest() {
        return new AddFollowingRequest("user1", "user2");
    }
}