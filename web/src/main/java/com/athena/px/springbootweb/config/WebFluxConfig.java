package com.athena.px.springbootweb.config;

import com.athena.px.springbootweb.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/23 16:46
 */
@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {


    public final UserHandler userHandler;

    public WebFluxConfig(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> indexRouterFuncction(){
        RouterFunction<ServerResponse> routerFunction =
                RouterFunctions.route(
                        GET("/getuser").and(accept(MediaType.APPLICATION_JSON)),userHandler::getUser)
                        .andRoute(POST("/createuser"),userHandler::createUser);
        return routerFunction;
    }
}
