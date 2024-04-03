package com.uala.microblogging.application.port.dto;

import com.uala.microblogging.model.Message;
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
public final class MessageEntity {
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(length = 280)
    private String text;
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity createdBy;

    public MessageEntity() {
    }

    public MessageEntity(final String text, final LocalDateTime creationDate, final UserEntity createdBy) {
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

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public Message toMessage() {
        return Message.Builder.builder()
                .text(this.text)
                .creationDate(this.creationDate)
                .createdBy(this.createdBy.toUser())
                .uuid(this.uuid)
                .build();
    }
}
