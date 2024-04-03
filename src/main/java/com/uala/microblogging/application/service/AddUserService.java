package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.in.AddUserUseCase;
import com.uala.microblogging.application.port.out.UserRepository;
import com.uala.microblogging.model.User;
import com.uala.microblogging.application.port.dto.UserEntity;

public class AddUserService implements AddUserUseCase {
    private final UserRepository userRepository;

    public AddUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(final User user) {
        this.userRepository.save(UserEntity.from(user));
    }
}
