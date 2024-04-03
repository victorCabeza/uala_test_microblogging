package com.uala.microblogging.application.service;


import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.AddMessageRequest;
import com.uala.microblogging.application.port.dto.AddUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.uala.microblogging.application.port.dto.AddUserRequestTest.getAddUserRequest;
import static com.uala.microblogging.application.port.dto.UserDtoTest.getTestUserDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddUserServiceTest {
    @Mock
    private UserRepository userRepository;
    private AddUserService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.service = new AddUserService(userRepository);
    }
    @Test
    void givenAddUserRequestWhenAddUserThenSuccess() {
        // given
        final AddUserRequest request = getAddUserRequest();
        when(this.userRepository.findById(any())).thenReturn(Optional.of(getTestUserDto()));

        // when
        this.service.add(request);

        // then
        verify(this.userRepository).save(any());
    }
}