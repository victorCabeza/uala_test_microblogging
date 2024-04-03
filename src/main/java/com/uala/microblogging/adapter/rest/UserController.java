package com.uala.microblogging.adapter.rest;

import com.uala.microblogging.application.port.dto.AddUserRequest;
import com.uala.microblogging.application.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AddUserService adduserService;

    @PostMapping()
    public ResponseEntity<AddUserRequest> addUser(@RequestBody AddUserRequest request) {
        this.adduserService.add(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}
