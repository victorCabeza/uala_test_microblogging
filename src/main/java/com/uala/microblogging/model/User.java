package com.uala.microblogging.model;

import java.util.HashSet;
import java.util.Set;

public final class User {
    private final String id;
    private final String name;
    private final String lastName;
    private final Set<User> following;

    private User(Builder builder) {
        id = builder.id;
        name = builder.name;
        lastName = builder.lastName;
        following = buildFollowing(builder);
    }

    private Set<User> buildFollowing(final Builder builder) {
        if(builder.following != null)
            return builder.following;
        return new HashSet<>();
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String lastName() {
        return lastName;
    }

    public Set<User> following() {
        return following;
    }

    public void addFollowing(final User followingUser) {
        this.following.add(followingUser);
    }


    /**
     * {@code User} builder static inner class.
     */
    public static final class Builder {
        private String id;
        private String name;
        private String lastName;
        private Set<User> following;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder enabling method chaining.
         *
         * @param id the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder enabling method chaining.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code lastName} and returns a reference to this Builder enabling method chaining.
         *
         * @param lastName the {@code lastName} to set
         * @return a reference to this Builder
         */
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the {@code following} and returns a reference to this Builder enabling method chaining.
         *
         * @param following the {@code following} to set
         * @return a reference to this Builder
         */
        public Builder following(Set<User> following) {
            this.following = following;
            return this;
        }

        /**
         * Returns a {@code User} built from the parameters previously set.
         *
         * @return a {@code User} built with parameters of this {@code User.Builder}
         */
        public User build() {
            return new User(this);
        }
    }
}
