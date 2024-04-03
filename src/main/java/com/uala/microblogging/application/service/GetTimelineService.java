package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.in.GetTimelineUseCase;
import com.uala.microblogging.application.port.out.MessageRepository;
import com.uala.microblogging.application.port.dto.MessageEntity;
import com.uala.microblogging.model.Message;

import java.util.List;

public class GetTimelineService implements GetTimelineUseCase {
    private final MessageRepository messageRepository;

    public GetTimelineService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> get(final String userId) {
        final List<MessageEntity> messageEntityList = this.messageRepository.findMessagesByFollowedUsers(userId);
        return messageEntityList.stream().map(MessageEntity::toMessage).toList();
    }
}
