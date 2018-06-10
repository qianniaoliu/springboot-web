package com.athena.px.auth;

import com.athena.px.entity.SysUser;
import com.athena.px.jwt.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/6 16:11
 */
@Component
public class JwtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationSuccessHandler.class);

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.expire}")
    private Integer expire;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationSuccessHandler(){}

    public JwtAuthenticationSuccessHandler(String defaultTargetUrl){
        this.setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录校验成功!");
        String username = authentication.getName();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        String token = jwtTokenUtil.generatorToken(sysUser,expire);
        response.setHeader(header,token);
        Cookie cookie = new Cookie(header,token);
        response.addCookie(cookie);
        super.onAuthenticationSuccess(request,response,authentication);
    }

    public void handler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = this.getDefaultTargetUrl();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("forWordUrl")){
                    targetUrl = cookie.getValue();
                    break;
                }
            }
        }
        this.getRedirectStrategy().sendRedirect(request,response,targetUrl);
    }
}
