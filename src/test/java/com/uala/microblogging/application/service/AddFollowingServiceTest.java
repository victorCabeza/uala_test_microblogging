package com.uala.microblogging.application.service;


import com.uala.microblogging.application.port.GetUserUseCase;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.port.dto.UserEntity;
import com.uala.microblogging.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddFollowingServiceTest {
    private static final String USER_ID_FOLLOWING = "user2";
    @Mock
    private UserRepository userRepository;

    @Mock
    private GetUserUseCase getUserUseCase;

    private AddFollowingService service;

    @Captor
    private ArgumentCaptor<UserEntity> followerCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.service = new AddFollowingService(userRepository, getUserUseCase);
    }

    @Test
    void givenExistingFollowerWhenAddFollowingThenSuccess() {
        // given
        final User follower = getTestUser();
        final User following = getTestFollowingUser();
        when(this.getUserUseCase.get(follower.id())).thenReturn(Optional.of(follower));
        when(this.getUserUseCase.get(following.id())).thenReturn(Optional.of(following));

        // when
        this.service.add(following, follower);

        // then
        verify(this.userRepository).save(followerCaptor.capture());
        final UserEntity followerUserEntity = followerCaptor.getValue();
        assertEquals(followerUserEntity.getId(), follower.id());
        assertTrue(containsFollowing(followerUserEntity, following));
    }

    @Test
    void givenNotExistingFollowerWhenAddFollowingThenIllegalArgumentException() {
        // given
        final User follower = getTestUser();
        final User following = getTestFollowingUser();
        when(this.getUserUseCase.get(follower.id())).thenReturn(Optional.empty());
        when(this.getUserUseCase.get(following.id())).thenReturn(Optional.of(following));

        // when
        assertThrows(IllegalArgumentException.class, () -> this.service.add(follower, following));
    }

    @Test
    void givenExistingFollowerWhenAddNotExistingFollowingThenIllegalArgumentException() {
        // given
        final User follower = getTestUser();
        final User following = getTestFollowingUser();
        when(this.getUserUseCase.get(follower.id())).thenReturn(Optional.of(follower));
        when(this.getUserUseCase.get(following.id())).thenReturn(Optional.empty());

        // when
        assertThrows(IllegalArgumentException.class, () -> this.service.add(follower, following));
    }

    private boolean containsFollowing(final UserEntity followerUserEntity, final User following) {
        return followerUserEntity.getFollowing().stream().anyMatch(u -> u.getId().equals(following.id()));
    }

    public static User getTestFollowingUser() {
        return User.Builder.builder()
                .id(USER_ID_FOLLOWING)
                .name("name following")
                .lastName("lastName following")
                .build();
    }
}