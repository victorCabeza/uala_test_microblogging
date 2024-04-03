package com.uala.microblogging.application.port;

import com.uala.microblogging.application.port.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageDto, UUID> {
}
