package com.uala.microblogging.application.port.dto;

import com.uala.microblogging.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;
    private String name;
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<UserEntity> following;

    public UserEntity() {}

    public UserEntity(final String id, final String name, final String lastName, final Set<UserEntity> following) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.following = following;
    }

    public static UserEntity from(final User user) {
        return new UserEntity(user.id(), user.name(),user.lastName(), buildFollowing(user.following()));
    }

    public User toUser() {
        return User.Builder.builder()
                .id(this.id)
                .name(this.name)
                .lastName(this.lastName)
                .following(buildFollowingToUser(this.following))
                .build();
    }

    private Set<User> buildFollowingToUser(final Set<UserEntity> following) {
        if(following != null)
            return following.stream().map(UserEntity::toUser).collect(Collectors.toSet());
        return Set.of();
    }

    private static Set<UserEntity> buildFollowing(final Set<User> userFollowing) {
        return userFollowing.stream().map(UserEntity::from).collect(Collectors.toSet());
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

    public Set<UserEntity> getFollowing() {
        return following;
    }
}
