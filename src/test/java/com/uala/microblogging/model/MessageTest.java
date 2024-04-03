package com.uala.microblogging.model;


import com.uala.microblogging.model.exceptions.TextOversizeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {

    public static final String UNDER_LIMIT_TEXT = "Under Limit text";
    public static final String OVERSIZED_TEXT = """
Lorem ipsum dolor sit amet, consectetur adipiscing elit,
sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
Duis aute irure dolor in reprehenderit in volupta""";
    public static final String USER_ID = "user1";

    @Test
    void givenUnderLimit280TextSizeMessageWhenCreateMessageThenSuccess() {
        // given
        final LocalDateTime localDateTime = getTestLocalDateTime();
        final User user = getTestUser();
        // when
        final Message message = new Message(UNDER_LIMIT_TEXT, localDateTime, user);

        // then
        assertNotNull(message);
    }

    @Test
    void givenAfterLimit280TextSizeMessageWhenCreateMessageThenSuccess() {
        // given
        final LocalDateTime localDateTime = getTestLocalDateTime();
        final User user = getTestUser();


        // then
        assertThrows(TextOversizeException.class, () -> {
            // when
            new Message(OVERSIZED_TEXT, localDateTime, user);
        });
    }

    @Test
    void givenNullTextMessageWhenCreateMessageThenIllegalArgumentException() {
        // given
        final LocalDateTime localDateTime = null;
        final User user = getTestUser();


        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            new Message(null, localDateTime, user);
        });
    }

    @Test
    void givenNullCreationDateWhenCreateMessageThenIllegalArgumentException() {
        // given
        final String text = null;
        final LocalDateTime localDateTime = getTestLocalDateTime();
        final User user = getTestUser();


        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            new Message(text, localDateTime, user);
        });
    }

    @Test
    void givenNullUserWhenCreateMessageThenIllegalArgumentException() {
        // given
        final LocalDateTime localDateTime = getTestLocalDateTime();
        final User user = null;


        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            new Message(UNDER_LIMIT_TEXT, localDateTime, user);
        });
    }

    public static User getTestUser() {
        return User.Builder.builder()
                .id(USER_ID)
                .name("name")
                .lastName("lastName")
                .build();
    }

    private static LocalDateTime getTestLocalDateTime() {
        return LocalDateTime.now();
    }
}