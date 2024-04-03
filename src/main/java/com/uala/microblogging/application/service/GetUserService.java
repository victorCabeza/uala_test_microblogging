package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.GetUserUseCase;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.UserEntity;
import com.uala.microblogging.model.User;

import java.util.Optional;

public class GetUserService implements GetUserUseCase {
    private final UserRepository userRepository;

    public GetUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> get(final String userId) {
        final Optional<UserEntity> userEntity = this.userRepository.findById(userId);
        return  userEntity.map(UserEntity::toUser);

    }
}
