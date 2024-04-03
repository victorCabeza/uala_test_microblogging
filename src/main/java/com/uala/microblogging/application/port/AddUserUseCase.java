package com.uala.microblogging.application.port;


import com.uala.microblogging.application.port.dto.AddUserRequest;

public interface AddUserUseCase {
    void add(AddUserRequest request);
}
