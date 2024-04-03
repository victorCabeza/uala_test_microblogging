package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.AddUserUseCase;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.AddUserRequest;

public class AddUserService implements AddUserUseCase {
    private final UserRepository userRepository;

    public AddUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(final AddUserRequest request) {
        this.userRepository.save(request.toUserDto());
    }
}
