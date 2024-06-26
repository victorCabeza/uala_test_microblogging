package com.uala.microblogging.configuration;

import com.uala.microblogging.application.port.in.AddFollowingUseCase;
import com.uala.microblogging.application.port.in.AddUserUseCase;
import com.uala.microblogging.application.port.in.GetUserUseCase;
import com.uala.microblogging.application.port.out.UserRepository;
import com.uala.microblogging.application.service.AddFollowingService;
import com.uala.microblogging.application.service.AddUserService;
import com.uala.microblogging.application.service.GetUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public AddUserUseCase addUserUseCase(final UserRepository userRepository){
        return new AddUserService(userRepository);
    }

    @Bean
    public GetUserUseCase getUserUseCase(final UserRepository userRepository){
        return new GetUserService(userRepository);
    }

    @Bean
    public AddFollowingUseCase addFollowingUseCase(final UserRepository userRepository, final GetUserUseCase getUserUseCase){
        return new AddFollowingService(userRepository, getUserUseCase);
    }
}
