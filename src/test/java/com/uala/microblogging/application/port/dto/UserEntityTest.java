package com.uala.microblogging.application.port.dto;

import com.uala.microblogging.model.User;
import org.junit.jupiter.api.Test;

import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserEntityTest {

    @Test
    void givenUserDtoWhenToUserThenReturnSuccessUser() {
        // given
        final UserEntity userDto = getTestUserEntity();

        // when
        final User user = userDto.toUser();

        // then
        assertEquals(user.id(), userDto.getId());
        assertEquals(user.name(), userDto.getName());
        assertEquals(user.lastName(), userDto.getLastName());
    }

    @Test
    void givenUserDtoWithNullFollowingWhenToUserThenReturnSuccessUser() {
        // given
        final UserEntity userDto = new UserEntity();

        // when
        final User user = userDto.toUser();

        // then
        assertTrue(user.following().isEmpty());
    }

    @Test
    void givenUserWithNullFollowingWhenToUserEntityThenReturnSuccessUser() {
        // given
        final User user = User.Builder.builder().build();

        // when
        final UserEntity userEntity = UserEntity.from(user);

        // then
        assertTrue(userEntity.getFollowing().isEmpty());
    }

    public static UserEntity getTestUserEntity() {
        return UserEntity.from(getTestUser());
    }

}