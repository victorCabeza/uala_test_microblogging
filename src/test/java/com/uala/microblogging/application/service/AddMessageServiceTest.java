package com.uala.microblogging.application.service;


import com.uala.microblogging.application.port.MessageRepository;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.MessageEntity;
import com.uala.microblogging.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.uala.microblogging.adapter.rest.dto.AddMessageRequestTest.MESSAGE_TEXT;
import static com.uala.microblogging.application.port.dto.UserEntityTest.getTestUserEntity;
import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddMessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    private AddMessageService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.service = new AddMessageService(messageRepository, userRepository);
    }

    @Test
    void givenMessageWhenAddThenSuccess() {
        // given
        final Message message = getTestMessage();
        when(this.messageRepository.save(any())).thenReturn(getTestMessageEntity());
        when(this.userRepository.findById(any())).thenReturn(Optional.of(getTestUserEntity()));

        // when
        this.service.add(message);

        // then
        verify(this.messageRepository).save(any());
    }

    @Test
    void givenMessageWhenNotExistsUserThenThrowException() {
        // given
        final Message message = getTestMessage();
        when(this.messageRepository.save(any())).thenReturn(getTestMessageEntity());
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            this.service.add(message);
        });
    }


    public static MessageEntity getTestMessageEntity() {
        return new MessageEntity(MESSAGE_TEXT, LocalDateTime.now(), getTestUserEntity());
    }

    private Message getTestMessage() {
        return Message.Builder.builder()
                .text(MESSAGE_TEXT)
                .creationDate(LocalDateTime.now())
                .createdBy(getTestUser())
                .build();
    }
}