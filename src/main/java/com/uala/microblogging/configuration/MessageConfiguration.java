package com.uala.microblogging.configuration;

import com.uala.microblogging.application.port.MessageRepository;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.service.AddMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    @Bean
    public AddMessageService addMessageService(final MessageRepository messageRepository, final UserRepository userRepository){
        return new AddMessageService(messageRepository, userRepository);
    }
}
