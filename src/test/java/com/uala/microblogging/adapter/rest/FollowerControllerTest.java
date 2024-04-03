package com.uala.microblogging.adapter.rest;


import com.uala.microblogging.adapter.rest.dto.AddFollowingRequest;
import com.uala.microblogging.adapter.rest.dto.AddUserRequest;
import com.uala.microblogging.application.port.AddFollowingUseCase;
import com.uala.microblogging.application.port.AddUserUseCase;
import com.uala.microblogging.application.port.GetUserUseCase;
import com.uala.microblogging.application.service.AddFollowingService;
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
class FollowerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddFollowingUseCase addFollowingUseCase;


    @Test
    void givenFollowerAndFollowingDataWhenPostFollowingThenSuccess() throws Exception {
        // given
        final AddFollowingRequest request = getAddFollowingRequest();

        // when
        this.mockMvc.perform(
                post("/follow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated());

        // then
        verify(this.addFollowingUseCase).add(any(), any());
    }

    private AddFollowingRequest getAddFollowingRequest() {
        return new AddFollowingRequest("user1", "user2");
    }
}