package com.athena.px.springbootweb.handler;

import com.athena.px.springbootweb.domain.User;
import com.athena.px.springbootweb.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/23 15:55
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> getUser(ServerRequest request){
        System.out.println("111111");
        return null;
    }

    public Mono<ServerResponse> createUser(ServerRequest request){
        Mono<User> user = request.bodyToMono(User.class);

        return ServerResponse.ok().body(user.map(userRepository::createUser),Boolean.class);
    }
}
