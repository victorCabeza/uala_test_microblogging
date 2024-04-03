package com.uala.microblogging.application.port.in;

import com.uala.microblogging.model.Message;

public interface AddMessageUseCase {
    void add(Message message);
}
