package com.athena.px.filter;

import com.athena.px.auth.AuthUserDetailsService;
import com.athena.px.entity.SysUser;
import com.athena.px.jwt.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/6 12:12
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.expire}")
    private Integer expire;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthUserDetailsService userDetailsService;

    public JwtAuthenticationTokenFilter(){}


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String token = request.getHeader(header);
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(header)){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        logger.info("开始验证用户 ");
        if(!StringUtils.isEmpty(token) && SecurityContextHolder.getContext().getAuthentication() == null){
            SysUser sysUser = jwtTokenUtil.getToken(token);
            if(sysUser != null){
                String username = sysUser.getUsername();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(jwtTokenUtil.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("数据用户 " + username + " 成功通过验证");
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }else if(!StringUtils.isEmpty(token) && SecurityContextHolder.getContext().getAuthentication() != null){
            SysUser sysUser = jwtTokenUtil.getToken(token);
            if(sysUser == null){
                SecurityContextHolder.getContext().setAuthentication(null);
            }else{
                logger.info("用户 " + sysUser.getUsername() + " SESSION 和 TOKEN  成功通过验证");
                String refreshToken = jwtTokenUtil.refreshToken(token,expire);
                Cookie cookie = new Cookie(header,refreshToken);
                response.addCookie(cookie);
            }
        }

        filterChain.doFilter(request,response);
    }
}
