package com.uala.microblogging.application.port.dto;

import com.uala.microblogging.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDtoTest {

//    @Test
//    void givenUserDtoWhenToUserThenReturnSuccessUser() {
//        // given
//        final UserDto userDto = getTestUserDto();
//
//        // when
//        final User user = userDto.toUser();
//
//        // then
//        assertEquals(user.id(), userDto.getId());
//        assertEquals(user.name(), userDto.getName());
//        assertEquals(user.lastName(), userDto.getLastName());
//    }

    public static UserDto getTestUserDto() {
        return new UserDto("user1", "name", "lastname"/*, List.of()*/);
    }
}