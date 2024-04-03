package com.uala.microblogging.adapter.rest;

import com.uala.microblogging.adapter.rest.dto.AddUserRequest;
import com.uala.microblogging.adapter.rest.dto.GetUserResponse;
import com.uala.microblogging.application.port.AddUserUseCase;
import com.uala.microblogging.application.port.GetUserUseCase;
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

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AddUserUseCase adduserService;

    @Autowired
    private GetUserUseCase getUserUseCase;

    @PostMapping()
    public ResponseEntity<AddUserRequest> addUser(@RequestBody AddUserRequest request) {
        this.adduserService.add(request.toUser());
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUser(@PathVariable final String userId) {
        final Optional<User> user = this.getUserUseCase.get(userId);
        return user.map(value -> new ResponseEntity<>(GetUserResponse.from(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
