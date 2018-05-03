package com.athena.px.springbootweb.webflux;

import com.athena.px.springbootweb.handler.UserHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/23 16:19
 */
public class FluxController {

    private final UserHandler userHandler;

    public FluxController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public void test(){
        RouterFunction<ServerResponse> routerFunction =
                RouterFunctions.route(
                        GET("/getuser").and(accept(MediaType.APPLICATION_JSON)),userHandler::getUser)
                .andRoute(POST("/createuser").and(accept(MediaType.APPLICATION_JSON)),userHandler::createUser);
    }
}
