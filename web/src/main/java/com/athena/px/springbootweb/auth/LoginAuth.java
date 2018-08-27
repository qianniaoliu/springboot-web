//package com.athena.px.springbootweb.auth;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Description:
// * @Author: ShenLong
// * @CreateDate: 2018/5/10 14:40
// */
//@Component
//public class LoginAuth implements AuthenticationProvider {
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        Authentication auth = new UsernamePasswordAuthenticationToken(username,password,grantedAuthorities);
//        return auth;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
