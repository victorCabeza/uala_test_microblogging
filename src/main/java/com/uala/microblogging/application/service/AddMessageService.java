package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.AddMessageUseCase;
import com.uala.microblogging.application.port.MessageRepository;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.MessageEntity;
import com.uala.microblogging.application.port.dto.UserEntity;
import com.uala.microblogging.model.Message;

import java.time.LocalDateTime;
import java.util.Optional;

public class AddMessageService implements AddMessageUseCase {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public AddMessageService(final MessageRepository messageRepository, final UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(final Message message) {
        final String userId = message.getCreatedBy().id();
        final Optional<UserEntity> userDto = this.userRepository.findById(userId);
        this.messageRepository.save(new MessageEntity(message.getText(),
                LocalDateTime.now(),
                userDto.orElseThrow(() -> new IllegalArgumentException("User not exists with te id %s".formatted(userId)))
                ));
    }
}
