package com.uala.microblogging.model;

import com.uala.microblogging.model.exceptions.TextOversizeException;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.util.UUID;

public final class Message {
    private static final int TEXT_LIMIT = 280;

    private final UUID uuid;
    private final String text;
    private final LocalDateTime creationDate;
    private final User createdBy;

    private Message(Builder builder) {
        uuid = builder.uuid;
        text = builder.text;
        creationDate = builder.creationDate;
        createdBy = builder.createdBy;
        validate();
    }

    private void validate() {
        validateText();
        validateCreationDate();
        validateUser();
    }

    private void validateUser() {
        if (this.createdBy == null) {
            throw new IllegalArgumentException("User can not be null");
        }
    }

    private void validateCreationDate() {
        if (this.creationDate == null) {
            throw new IllegalArgumentException("CreatedBy can not be null");
        }
    }

    private void validateText() {
        if (Strings.isEmpty(this.text)) {
            throw new IllegalArgumentException("Text can not be null or empty");
        }
        if (this.text.length() > TEXT_LIMIT) {
            throw new TextOversizeException("Text length must be less than %s".formatted(TEXT_LIMIT));
        }
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public UUID getUuid() {
        return uuid;
    }

    /**
     * {@code Message} builder static inner class.
     */
    public static final class Builder {
        private UUID uuid;
        private String text;
        private LocalDateTime creationDate;
        private User createdBy;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        /**
         * Sets the {@code uuid} and returns a reference to this Builder enabling method chaining.
         *
         * @param uuid the {@code uuid} to set
         * @return a reference to this Builder
         */
        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        /**
         * Sets the {@code text} and returns a reference to this Builder enabling method chaining.
         *
         * @param text the {@code text} to set
         * @return a reference to this Builder
         */
        public Builder text(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets the {@code creationDate} and returns a reference to this Builder enabling method chaining.
         *
         * @param creationDate the {@code creationDate} to set
         * @return a reference to this Builder
         */
        public Builder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Sets the {@code createdBy} and returns a reference to this Builder enabling method chaining.
         *
         * @param createdBy the {@code createdBy} to set
         * @return a reference to this Builder
         */
        public Builder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        /**
         * Returns a {@code Message} built from the parameters previously set.
         *
         * @return a {@code Message} built with parameters of this {@code Message.Builder}
         */
        public Message build() {
            return new Message(this);
        }
    }
}
