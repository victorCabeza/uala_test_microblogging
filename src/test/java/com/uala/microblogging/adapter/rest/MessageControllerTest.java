package com.uala.microblogging.adapter.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uala.microblogging.adapter.rest.dto.AddMessageRequest;
import com.uala.microblogging.application.port.in.AddMessageUseCase;
import com.uala.microblogging.application.port.in.GetTimelineUseCase;
import com.uala.microblogging.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.uala.microblogging.adapter.rest.dto.AddMessageRequestTest.getAddMessageRequest;
import static com.uala.microblogging.model.MessageTest.USER_ID;
import static com.uala.microblogging.model.MessageTest.getTestMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddMessageUseCase addMessageUseCase;
    @MockBean
    private GetTimelineUseCase getTimelineUseCase;

    @Test
    void givenTextAndUserWhenPostMessageThenSuccess() throws Exception {
        // given
        final AddMessageRequest request = getAddMessageRequest();

        // when
        this.mockMvc.perform(
                post("/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated());

        // then
        verify(this.addMessageUseCase).add(any());
    }

    @Test
    void givenNotExistingUserIdWhenPostMessageThen400() throws Exception {
        // given
        final AddMessageRequest request = getAddMessageRequest();
        doThrow(new IllegalArgumentException()).when(this.addMessageUseCase).add(any());


        // when
        this.mockMvc.perform(
                        post("/message")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                .andExpect(status().isBadRequest());

        // then
        verify(this.addMessageUseCase).add(any());
    }



    @Test
    void givenExistingUserIdWhenGetMessagesByUserThenSuccess() throws Exception {
        // given

        final Message message = getTestMessage();
        when(this.getTimelineUseCase.get(any())).thenReturn(List.of(message));

        // when
        final String responseBody = mockMvc.perform(get("/message/user/%s".formatted(USER_ID)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("[{\"uuid\":\"72a9715a-455f-4a98-ac7f-08e212d226ac\",\"text\":\"Under Limit text\",\"creationDate\":\"2024-04-03T10:15:30\",\"userId\":\"user1\"}]", responseBody);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}