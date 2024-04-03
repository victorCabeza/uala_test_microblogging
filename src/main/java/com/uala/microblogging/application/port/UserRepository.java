package com.uala.microblogging.application.port;

import com.uala.microblogging.application.port.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDto, String> {
}
