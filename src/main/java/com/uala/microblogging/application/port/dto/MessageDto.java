package com.uala.microblogging.application.port.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
public final class MessageDto {
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(length = 280)
    private final String text;
    private final LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "id")
    private final UserDto createdBy;

    public MessageDto(final String text, final LocalDateTime creationDate, final UserDto createdBy) {
        this.text = text;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }
}
