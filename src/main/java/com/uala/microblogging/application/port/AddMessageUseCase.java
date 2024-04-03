package com.uala.microblogging.application.port;

import com.uala.microblogging.model.Message;

public interface AddMessageUseCase {
    void add(Message message);
}
