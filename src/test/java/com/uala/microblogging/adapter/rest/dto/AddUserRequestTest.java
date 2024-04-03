package com.uala.microblogging.adapter.rest.dto;


import com.uala.microblogging.model.User;
import org.junit.jupiter.api.Test;

import static com.uala.microblogging.model.MessageTest.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserRequestTest {
    @Test
    void givenAddUserRequestWhenToUserDtoThenSuccess() {
        // given
        final AddUserRequest request = getAddUserRequest();

        // when
        final User user = request.toUser();

        // then
        assertEquals(request.id(), user.id());
        assertEquals(request.name(), user.name());
        assertEquals(request.lastName(), user.lastName());
    }



    public static AddUserRequest getAddUserRequest() {
        return new AddUserRequest(USER_ID, "name", "lastName");
    }
}