package com.uala.microblogging.application.port.dto;

public record AddUserRequest(String id, String name, String lastName) {
    public UserDto toUserDto() {
        return new UserDto(id, name,lastName);
    }
}
