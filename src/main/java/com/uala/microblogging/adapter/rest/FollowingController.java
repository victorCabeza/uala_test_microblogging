package com.uala.microblogging.adapter.rest;

import com.uala.microblogging.adapter.rest.dto.AddFollowingRequest;
import com.uala.microblogging.application.port.in.AddFollowingUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowingController {

    @Autowired
    private AddFollowingUseCase addFollowingUseCase;


    @PostMapping()
    public ResponseEntity<AddFollowingRequest> addFollowing(@RequestBody AddFollowingRequest request) {
        this.addFollowingUseCase.add(request.buildFollowingUser(), request.buildFollowerUser());
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
