package com.uala.microblogging.adapter.rest;

import com.uala.microblogging.application.port.dto.AddMessageRequest;
import com.uala.microblogging.application.service.AddMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private AddMessageService addMessageService;

    @PostMapping()
    public ResponseEntity<AddMessageRequest> addMessage(@RequestBody AddMessageRequest request) {
        this.addMessageService.add(request.toMessage());
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}
