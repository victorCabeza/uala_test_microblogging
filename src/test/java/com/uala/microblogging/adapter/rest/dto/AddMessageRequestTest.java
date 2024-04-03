package com.uala.microblogging.adapter.rest.dto;


import com.uala.microblogging.model.Message;
import org.junit.jupiter.api.Test;

import static com.uala.microblogging.model.MessageTest.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddMessageRequestTest {

    public static final String MESSAGE_TEXT = "text";
    @Test
    void givenAddMessageRequestWhenToMessageThenSuccess() {
        // given
        final AddMessageRequest addMessageRequest = getAddMessageRequest();

        // when
        final Message message = addMessageRequest.toMessage();

        // then
        assertEquals(addMessageRequest.text(), message.getText());
        assertEquals(addMessageRequest.userId(), message.getCreatedBy().id());
        assertNotNull(message.getCreationDate());
    }

    public static AddMessageRequest getAddMessageRequest() {
        return new AddMessageRequest(MESSAGE_TEXT, USER_ID);
    }
}