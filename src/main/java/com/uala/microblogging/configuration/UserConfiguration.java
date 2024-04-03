package com.uala.microblogging.configuration;

import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.service.AddUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public AddUserService addUserService(final UserRepository userRepository){
        return new AddUserService(userRepository);
    }
}
