package com.uala.microblogging.adapter.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uala.microblogging.application.port.dto.AddMessageRequest;
import com.uala.microblogging.application.port.dto.AddUserRequest;
import com.uala.microblogging.application.service.AddMessageService;
import com.uala.microblogging.application.service.AddUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.uala.microblogging.adapter.rest.MessageControllerTest.asJsonString;
import static com.uala.microblogging.application.port.dto.AddUserRequestTest.getAddUserRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddUserService service;

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
        verify(this.service).add(any());
    }
}