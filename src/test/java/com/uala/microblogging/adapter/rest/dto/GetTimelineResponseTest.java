package com.uala.microblogging.adapter.rest.dto;

import com.uala.microblogging.model.Message;
import org.junit.jupiter.api.Test;

import static com.uala.microblogging.model.MessageTest.getTestMessage;
import static org.junit.jupiter.api.Assertions.*;

class GetTimelineResponseTest {
    @Test
    void givenMessageWhenFromThenSuccess() {
        // given
        final Message message = getTestMessage();

        // when
        final GetTimelineResponse response = GetTimelineResponse.from(message);

        // Assert
        assertNotNull(response);
        assertEquals(message.getText(), response.text());
        assertEquals(message.getCreationDate(), response.creationDate());
        assertEquals(message.getCreatedBy().id(), response.userId());
    }

}