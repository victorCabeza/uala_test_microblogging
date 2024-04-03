package com.uala.microblogging.adapter.rest;


import com.uala.microblogging.adapter.rest.dto.AddUserRequest;
import com.uala.microblogging.application.port.in.AddUserUseCase;
import com.uala.microblogging.application.port.in.GetUserUseCase;
import com.uala.microblogging.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.uala.microblogging.adapter.rest.MessageControllerTest.asJsonString;
import static com.uala.microblogging.adapter.rest.dto.AddUserRequestTest.getAddUserRequest;
import static com.uala.microblogging.application.service.AddFollowingServiceTest.getTestFollowingUser;
import static com.uala.microblogging.model.MessageTest.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddUserUseCase addUserUseCase;

    @MockBean
    private GetUserUseCase getUserUseCase;

    @Test
    void givenUserDataWhenPostUserThenSuccess() throws Exception {
        // given
        final AddUserRequest request = getAddUserRequest();

        // when
        this.mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated());

        // then
        verify(this.addUserUseCase).add(any());
    }

    @Test
    void givenExistingUserIdWhenGetUserThenSuccess() throws Exception {
        // given
        final User follower = getTestUser();
        final User following = getTestFollowingUser();

        follower.addFollowing(following);

        when(this.getUserUseCase.get(any())).thenReturn(Optional.of(follower));

        // when
        final String responseBody = mockMvc.perform(get("/user/%s".formatted(follower.id())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("{\"id\":\"user1\",\"name\":\"name\",\"lastName\":\"lastName\",\"following\":[{\"id\":\"user2\",\"name\":\"name following\",\"lastName\":\"lastName following\",\"following\":[]}]}", responseBody);
    }



    @Test
    void givenNotExistingUserIdWhenGetUserThenSuccess() throws Exception {
        // given
        when(this.getUserUseCase.get(any())).thenReturn(Optional.empty());

        // when
        mockMvc.perform(get("/user/%s".formatted("any")))
                .andExpect(status().isNotFound());
    }
}