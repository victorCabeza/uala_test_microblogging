package com.uala.microblogging.adapter.rest;

import com.uala.microblogging.adapter.rest.dto.AddMessageRequest;
import com.uala.microblogging.adapter.rest.dto.GetFollowingMessageResponse;
import com.uala.microblogging.adapter.rest.dto.GetUserResponse;
import com.uala.microblogging.application.port.AddMessageUseCase;
import com.uala.microblogging.application.port.GetFollowingMessagesUseCase;
import com.uala.microblogging.model.Message;
import com.uala.microblogging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private AddMessageUseCase addMessageUseCase;

    @Autowired
    private GetFollowingMessagesUseCase getFollowingMessagesUseCase;

    @PostMapping()
    public ResponseEntity<AddMessageRequest> addMessage(@RequestBody AddMessageRequest request) {
        this.addMessageUseCase.add(request.toMessage());
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }


    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetFollowingMessageResponse>> getUser(@PathVariable final String userId) {
        final List<Message> messages = this.getFollowingMessagesUseCase.get(userId);
        return new ResponseEntity<>(messages.stream().map(GetFollowingMessageResponse::from).toList(), HttpStatus.OK);
    }
}
