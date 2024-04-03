package com.uala.microblogging.application.port.dto;

import com.uala.microblogging.model.User;
import org.junit.jupiter.api.Test;

import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

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





    public static UserEntity getTestUserEntity() {
        return UserEntity.from(getTestUser());
    }

}