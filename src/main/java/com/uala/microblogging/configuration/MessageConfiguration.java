package com.uala.microblogging.configuration;

import com.uala.microblogging.application.port.AddMessageUseCase;
import com.uala.microblogging.application.port.GetTimelineUseCase;
import com.uala.microblogging.application.port.MessageRepository;
import com.uala.microblogging.application.port.UserRepository;
import com.uala.microblogging.application.service.AddMessageService;
import com.uala.microblogging.application.service.GetTimelineService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    @Bean
    public AddMessageUseCase addMessageService(final MessageRepository messageRepository, final UserRepository userRepository){
        return new AddMessageService(messageRepository, userRepository);
    }

    @Bean
    public GetTimelineUseCase getTimeline(final MessageRepository messageRepository){
        return new GetTimelineService(messageRepository);
    }
}
