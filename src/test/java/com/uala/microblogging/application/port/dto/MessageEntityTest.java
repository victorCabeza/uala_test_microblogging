package com.uala.microblogging.application.port.dto;


import com.uala.microblogging.model.Message;
import org.junit.jupiter.api.Test;

import static com.uala.microblogging.application.service.AddMessageServiceTest.getTestMessageEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageEntityTest {
    @Test
    void givenMessageEntityWhenToMessageThenSuccess() {
        // given
        final MessageEntity messageEntity = getTestMessageEntity();

        // when
        final Message message = messageEntity.toMessage();

        // then
        assertEquals(messageEntity.getText(), message.getText());
        assertEquals(messageEntity.getCreatedBy().getId(), message.getCreatedBy().id());
        assertEquals(messageEntity.getCreationDate(), message.getCreationDate());
        assertEquals(messageEntity.getUuid(), message.getUuid());
    }
}