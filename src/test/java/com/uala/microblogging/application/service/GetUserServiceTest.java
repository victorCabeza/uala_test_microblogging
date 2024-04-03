package com.uala.microblogging.application.service;

import com.uala.microblogging.application.port.in.GetUserUseCase;
import com.uala.microblogging.application.port.out.UserRepository;
import com.uala.microblogging.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.uala.microblogging.application.port.dto.UserEntityTest.getTestUserEntity;
import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class GetUserServiceTest {
    @Mock
    private UserRepository userRepository;

    private GetUserUseCase service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.service = new GetUserService(userRepository);
    }

    @Test
    void givenExistingUserWhenGetThenObtainExistingUser() {
        // given
        final User user = getTestUser();
        when(this.userRepository.findById(any())).thenReturn(Optional.of(getTestUserEntity()));

        // when
        final User userReturned = this.service.get(user.id()).get();

        // then
        assertEquals(user.id() , userReturned.id());
    }
}