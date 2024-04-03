package com.uala.microblogging.application.port.out;

import com.uala.microblogging.application.port.dto.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
    @Query("SELECT m FROM MessageEntity m WHERE m.createdBy IN (SELECT u.following FROM UserEntity u WHERE u.id = :userId) ORDER BY m.creationDate desc")
    List<MessageEntity> findMessagesByFollowedUsers(@Param("userId") String userId);
}
