package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.uala.microblogging.application.service.AddFollowingServiceTest.getTestFollowingUser;
import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetUserResponseTest {
    @Test
    void givenUserWhenFromThenSuccess() {
        // given
        final User follower = getTestUser();
        final User following = getTestFollowingUser();
        follower.addFollowing(following);


        // when
        final GetUserResponse response = GetUserResponse.from(follower);

        // then
        assertEquals(follower.id(), response.id());
        assertEquals(follower.name(), response.name());
        assertEquals(follower.lastName(), response.lastName());
        assertNotNull(response.following());
        assertEquals(1, response.following().size());
    }
}