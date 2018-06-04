package com.athena.px.config;

import com.athena.px.auth.AuthUserDetailsService;
import com.athena.px.auth.UserAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/24 9:24
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthenticationProvider provider;
    private final AuthUserDetailsService authUserDetailsService;


    public WebSecurityConfig(UserAuthenticationProvider provider, AuthUserDetailsService authUserDetailsService) {
        this.provider = provider;
        this.authUserDetailsService = authUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置用户验证跳转路径
        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/error/notuser")
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()//设置任何用户都可以访问静态资源
        .antMatchers("/admin/**").hasRole("ADMIN")//设置具有ADMIN权限的用户才能访问的URL
        .antMatchers("/db/**").access("hasRole('DBA') and hasRole('ADMIN')")
                .anyRequest().authenticated().and().formLogin();

        //logout
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .addLogoutHandler(new CookieClearingLogoutHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailsService);
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/img/**");
    }
}
