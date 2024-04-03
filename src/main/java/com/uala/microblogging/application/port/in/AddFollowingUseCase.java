package com.uala.microblogging.application.port.in;

import com.uala.microblogging.model.User;

public interface AddFollowingUseCase {
    void add(User following, User follower);
}
