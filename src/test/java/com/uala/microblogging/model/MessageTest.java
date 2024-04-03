package com.uala.microblogging.model;


import com.uala.microblogging.model.exceptions.TextOversizeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

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
    public static final String CREATION_DATE = "2024-04-03T10:15:30";

    @Test
    void givenUnderLimit280TextSizeMessageWhenCreateMessageThenSuccess() {
        // given
        final Message message = getTestMessage();

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
            Message.Builder.builder()
                    .text(OVERSIZED_TEXT)
                    .creationDate(localDateTime)
                    .createdBy(user)
                    .build();
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
            Message.Builder.builder()
                    .creationDate(localDateTime)
                    .createdBy(user)
                    .build();
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
            Message.Builder.builder()
                    .text(text)
                    .creationDate(localDateTime)
                    .createdBy(user)
                    .build();
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
            Message.Builder.builder()
                    .text(UNDER_LIMIT_TEXT)
                    .creationDate(localDateTime)
                    .createdBy(user)
                    .build();
        });
    }

    @Test
    void givenNullCreationDateMessageWhenCreateMessageThenException() {
        // given
        final User user = getTestUser();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            Message.Builder.builder()
                    .text(UNDER_LIMIT_TEXT)
                    .createdBy(user)
                    .build();
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
        return LocalDateTime.parse(CREATION_DATE, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static Message getTestMessage() {
        return Message.Builder.builder()
                .uuid(UUID.fromString("72a9715a-455f-4a98-ac7f-08e212d226ac"))
                .text(UNDER_LIMIT_TEXT)
                .creationDate(getTestLocalDateTime())
                .createdBy(getTestUser())
                .build();
    }
}