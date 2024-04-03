package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.AddFollowingUseCase;
import com.uala.microblogging.application.port.GetUserUseCase;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.UserEntity;
import com.uala.microblogging.model.User;

import java.util.Optional;

public class AddFollowingService implements AddFollowingUseCase {
    private final UserRepository userRepository;
    private final GetUserUseCase getUserUseCase;

    public AddFollowingService(final UserRepository userRepository, final GetUserUseCase getUserUseCase) {
        this.userRepository = userRepository;
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public void add(final User following, final User follower) {
        final User followingUser = getUserOrThrow(following);
        final User followerUser = getUserOrThrow(follower);
        followerUser.addFollowing(followingUser);
        this.userRepository.save(UserEntity.from(followerUser));
    }

    private User getUserOrThrow(final User user) {
        final Optional<User> followerUserEntity = this.getUserUseCase.get(user.id());
        return followerUserEntity.orElseThrow(() -> new IllegalArgumentException("User with id %s not exists.".formatted(user.id())));
    }
}
