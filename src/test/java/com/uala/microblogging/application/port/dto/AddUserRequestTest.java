package com.uala.microblogging.application.port.dto;


import org.junit.jupiter.api.Test;

import static com.uala.microblogging.model.MessageTest.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserRequestTest {
    @Test
    void givenAddUserRequestWhenToUserDtoThenSuccess() {
        // given
        final AddUserRequest request = getAddUserRequest();

        // when
        final UserDto userDto = request.toUserDto();

        // then
        assertEquals(request.id(), userDto.getId());
        assertEquals(request.name(), userDto.getName());
        assertEquals(request.lastName(), userDto.getLastName());
    }



    public static AddUserRequest getAddUserRequest() {
        return new AddUserRequest(USER_ID, "name", "lastName");
    }
}