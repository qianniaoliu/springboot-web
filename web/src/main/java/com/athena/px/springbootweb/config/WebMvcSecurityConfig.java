//package com.athena.px.springbootweb.config;
//
//import com.athena.px.springbootweb.auth.LoginAuth;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @Description:
// * @Author: ShenLong
// * @CreateDate: 2018/5/10 14:26
// */
//@Configuration
//@EnableWebSecurity
//public class WebMvcSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private LoginAuth loginAuth;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        System.out.println("进入Security安全验证!");
//        http.authorizeRequests().antMatchers("/like").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.formLogin().loginPage("/login")
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .failureUrl("/login-error");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(loginAuth);
//    }
//}
