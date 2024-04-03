package com.uala.microblogging.model;

import java.util.List;

public final class User {
    private final String id;
    private final String name;
    private final String lastName;
    private final List<User> following;

    private User(Builder builder) {
        id = builder.id;
        name = builder.name;
        lastName = builder.lastName;
        following = builder.following;
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

    public List<User> following() {
        return following;
    }


    /**
     * {@code User} builder static inner class.
     */
    public static final class Builder {
        private String id;
        private String name;
        private String lastName;
        private List<User> following;

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
        public Builder following(List<User> following) {
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
