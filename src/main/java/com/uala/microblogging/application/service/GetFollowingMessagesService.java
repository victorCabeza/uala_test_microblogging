package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.GetFollowingMessagesUseCase;
import com.uala.microblogging.application.port.MessageRepository;
import com.uala.microblogging.application.port.dto.MessageEntity;
import com.uala.microblogging.model.Message;

import java.util.List;

public class GetFollowingMessagesService implements GetFollowingMessagesUseCase {
    private final MessageRepository messageRepository;

    public GetFollowingMessagesService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> get(final String userId) {
        final List<MessageEntity> messageEntityList = this.messageRepository.findMessagesByFollowedUsers(userId);
        return messageEntityList.stream().map(MessageEntity::toMessage).toList();
    }
}
