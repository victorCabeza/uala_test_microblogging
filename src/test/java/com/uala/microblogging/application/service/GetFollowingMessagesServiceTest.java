package com.uala.microblogging.application.service;


import com.uala.microblogging.application.port.GetFollowingMessagesUseCase;
import com.uala.microblogging.application.port.MessageRepository;
import com.uala.microblogging.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.uala.microblogging.application.service.AddMessageServiceTest.getTestMessageEntity;
import static com.uala.microblogging.model.MessageTest.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GetFollowingMessagesServiceTest {
    @Mock
    private MessageRepository messageRepository;

    private GetFollowingMessagesUseCase getFollowingMessagesUseCase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.getFollowingMessagesUseCase = new GetFollowingMessagesService(messageRepository);
    }

    @Test
    void givenExistingUserIdWhenGetThenObtainFollowingMessageList() {
        // given
        when(messageRepository.findMessagesByFollowedUsers(any())).thenReturn(List.of(getTestMessageEntity()));

        // when
        final List<Message> messages = this.getFollowingMessagesUseCase.get(USER_ID);

        // then
        assertEquals(1, messages.size());
    }
}