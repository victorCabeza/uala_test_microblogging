package com.uala.microblogging.model;


import org.junit.jupiter.api.Test;

import static com.uala.microblogging.application.service.AddFollowingServiceTest.getTestFollowingUser;
import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    @Test
    void givenNotExistingFollowingWhenAddFollowingThenSuccess() {
        // given
        final User user = getTestUser();
        final User following = getTestFollowingUser();

        // when
        user.addFollowing(following);

        // then
        assertTrue(user.following().contains(following));
    }
}