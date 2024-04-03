package com.uala.microblogging.application.port;

import com.uala.microblogging.model.User;

public interface AddFollowingUseCase {
    void add(User following, User follower);
}
