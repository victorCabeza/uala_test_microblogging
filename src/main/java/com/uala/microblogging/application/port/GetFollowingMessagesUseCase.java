package com.uala.microblogging.application.port;

import com.uala.microblogging.model.Message;

import java.util.List;

public interface GetFollowingMessagesUseCase {
    List<Message> get(String userId);
}
