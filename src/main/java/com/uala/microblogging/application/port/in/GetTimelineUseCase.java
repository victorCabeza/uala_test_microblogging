package com.uala.microblogging.application.port.in;

import com.uala.microblogging.model.Message;

import java.util.List;

public interface GetTimelineUseCase {
    List<Message> get(String userId);
}
