package com.uala.microblogging.adapter.rest;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uala.microblogging.adapter.rest.dto.AddFollowingRequest;
import com.uala.microblogging.adapter.rest.dto.AddMessageRequest;
import com.uala.microblogging.adapter.rest.dto.AddUserRequest;
import com.uala.microblogging.adapter.rest.dto.GetTimelineResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.uala.microblogging.adapter.rest.MessageControllerTest.asJsonString;
import static com.uala.microblogging.adapter.rest.dto.AddMessageRequestTest.MESSAGE_TEXT;
import static com.uala.microblogging.adapter.rest.dto.AddUserRequestTest.getAddUserRequest;
import static com.uala.microblogging.model.MessageTest.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TimelineIntegrationTest {
    private static final String FOLLOWING_USER_ID = "user2";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenUserWithFollowingWhenFollowingAddMessageThenUserSeesMessageInTimeline() throws Exception {
        // given
        configureUsers();
        addMessage();


        // when
        final String responseBody = mockMvc.perform(get("/message/user/%s".formatted(USER_ID)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<GetTimelineResponse> timeline = objectMapper.readValue(responseBody, new TypeReference<>() {
        });


        // then
        assertEquals(1, timeline.size());
    }

    private void addMessage() throws Exception {
        final AddMessageRequest request = new AddMessageRequest(MESSAGE_TEXT, FOLLOWING_USER_ID);

        this.mockMvc.perform(
                post("/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated());
    }

    private void configureUsers() throws Exception {
        final AddUserRequest addUserRequest = getAddUserRequest();
        addUser(addUserRequest);
        final AddUserRequest requestFollowing = getAddUserRequestFollowing();
        addUser(requestFollowing);

        final AddFollowingRequest addFollowingRequest = new AddFollowingRequest(FOLLOWING_USER_ID, USER_ID);

        this.mockMvc.perform(
                        post("/follow")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(addFollowingRequest)))
                .andExpect(status().isCreated());
    }

    private void addUser(AddUserRequest request) throws Exception {
        this.mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                .andExpect(status().isCreated());
    }


    public static AddUserRequest getAddUserRequestFollowing() {
        return new AddUserRequest(FOLLOWING_USER_ID, "name following", "lastName following");
    }

}