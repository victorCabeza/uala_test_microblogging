package com.uala.microblogging.application.port.in;


import com.uala.microblogging.model.User;

public interface AddUserUseCase {
    void add(User user);
}
