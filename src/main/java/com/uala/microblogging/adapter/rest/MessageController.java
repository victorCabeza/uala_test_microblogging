package com.uala.microblogging.adapter.rest;

import com.uala.microblogging.adapter.rest.dto.AddMessageRequest;
import com.uala.microblogging.adapter.rest.dto.GetTimelineResponse;
import com.uala.microblogging.application.port.in.AddMessageUseCase;
import com.uala.microblogging.application.port.in.GetTimelineUseCase;
import com.uala.microblogging.model.Message;
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

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private AddMessageUseCase addMessageUseCase;

    @Autowired
    private GetTimelineUseCase getTimelineUseCase;

    @PostMapping()
    public ResponseEntity<AddMessageRequest> addMessage(@RequestBody AddMessageRequest request) {
        this.addMessageUseCase.add(request.toMessage());
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }


    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetTimelineResponse>> getTimeline(@PathVariable final String userId) {
        final List<Message> messages = this.getTimelineUseCase.get(userId);
        return new ResponseEntity<>(messages.stream().map(GetTimelineResponse::from).toList(), HttpStatus.OK);
    }
}
