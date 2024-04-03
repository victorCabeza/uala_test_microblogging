package com.uala.microblogging.application.service;


import com.uala.microblogging.application.port.out.UserRepository;
import com.uala.microblogging.application.port.dto.UserEntity;
import com.uala.microblogging.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.uala.microblogging.model.MessageTest.getTestUser;
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
        final User user = getTestUser();
        when(this.userRepository.findById(any())).thenReturn(Optional.of(UserEntity.from(user)));

        // when
        this.service.add(user);

        // then
        verify(this.userRepository).save(any());
    }
}