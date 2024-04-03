package com.uala.microblogging.application.port.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserDto {

    @Id
    private String id;
    private String name;
    private String lastName;
    //private final List<UserDto> following;

    public UserDto() {}

    public UserDto(final String id, final String name, final String lastName/*, final List<UserDto> following*/) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        //this.following = following;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

//    public List<UserDto> getFollowing() {
//        return following;
//    }
//
//    public User toUser() {
//        return new User(this.id, this.name, this.lastName,
//                this.following.stream().map(UserDto::toUser).toList()
//                );
//    }
}
