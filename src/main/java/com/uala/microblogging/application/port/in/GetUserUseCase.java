package com.uala.microblogging.application.port.in;

import com.uala.microblogging.model.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> get(final String userId);
}
