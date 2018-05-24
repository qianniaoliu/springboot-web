package com.athena.px.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/24 14:52
 */
public class DefaultHandlerInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(DefaultHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入默认拦截器！");
        return true;
    }
}
