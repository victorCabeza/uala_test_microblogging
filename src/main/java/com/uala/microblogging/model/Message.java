package com.uala.microblogging.model;

import com.uala.microblogging.model.exceptions.TextOversizeException;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.util.Objects;

public record Message(String text, LocalDateTime creationDate, User createdBy) {
    private static final int TEXT_LIMIT = 280;

    public Message(final String text, final LocalDateTime creationDate, final User createdBy) {
        this.text = text;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
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

}
