package com.uala.microblogging.application.port;


import com.uala.microblogging.model.User;

public interface AddUserUseCase {
    void add(User user);
}
