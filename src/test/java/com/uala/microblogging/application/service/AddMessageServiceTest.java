package com.uala.microblogging.application.service;


import com.uala.microblogging.application.port.MessageRepository;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.AddMessageRequest;
import com.uala.microblogging.application.port.dto.MessageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.uala.microblogging.application.port.dto.AddMessageRequestTest.MESSAGE_TEXT;
import static com.uala.microblogging.application.port.dto.AddMessageRequestTest.getAddMessageRequest;
import static com.uala.microblogging.application.port.dto.UserDtoTest.getTestUserDto;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddMessageServiceTest {

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
        final AddMessageRequest request = getAddMessageRequest();
        when(this.messageRepository.save(any())).thenReturn(getTestMessageDto());
        when(this.userRepository.findById(any())).thenReturn(Optional.of(getTestUserDto()));

        // when
        this.service.add(request.toMessage());

        // then
        verify(this.messageRepository).save(any());
    }

    @Test
    void givenMessageWhenNotExistsUserThenThrowException() {
        // given
        final AddMessageRequest request = getAddMessageRequest();
        when(this.messageRepository.save(any())).thenReturn(getTestMessageDto());
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            this.service.add(request.toMessage());
        });
    }


    private static MessageDto getTestMessageDto() {
        return new MessageDto(MESSAGE_TEXT, LocalDateTime.now(), getTestUserDto());
    }
}