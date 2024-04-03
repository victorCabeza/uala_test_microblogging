package com.uala.microblogging.adapter.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uala.microblogging.adapter.rest.dto.AddMessageRequest;
import com.uala.microblogging.application.service.AddMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.uala.microblogging.adapter.rest.dto.AddMessageRequestTest.getAddMessageRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddMessageService service;

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
        verify(this.service).add(any());
    }

    @Test
    void givenNotExistingUserIdWhenPostMessageThen400() throws Exception {
        // given
        final AddMessageRequest request = getAddMessageRequest();
        doThrow(new IllegalArgumentException()).when(this.service).add(any());


        // when
        this.mockMvc.perform(
                        post("/message")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(request)))
                .andExpect(status().isBadRequest());

        // then
        verify(this.service).add(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}