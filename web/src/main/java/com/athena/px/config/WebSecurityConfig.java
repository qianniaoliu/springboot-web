package com.athena.px.config;

import com.athena.px.auth.AuthUserDetailsService;
import com.athena.px.auth.JwtAuthenticationSuccessHandler;
import com.athena.px.auth.RestAuthenticationEntryPoint;
import com.athena.px.auth.UserAuthenticationProvider;
import com.athena.px.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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


    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler(){
        return new JwtAuthenticationSuccessHandler("/index");
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //设置用户验证跳转路径
        http/*.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()*/.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/error/notuser")
                .successHandler(jwtAuthenticationSuccessHandler())
                .permitAll();

        http.authorizeRequests()//设置任何用户都可以访问静态资源
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/login",
                        "/**/*.html",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.jpg",
                        "/favicon.ico",
                        "/**/*.png")
                .permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")//设置具有ADMIN权限的用户才能访问的URL
        .antMatchers("/db/**").access("hasRole('DBA') and hasRole('ADMIN')")
                .anyRequest().authenticated().and().formLogin();


        //logout
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .addLogoutHandler(new CookieClearingLogoutHandler())
                .deleteCookies("ACCESS_TOKEN","JSESSIONID")
                .permitAll();

        //新增JWT权限拦截器
        http.addFilterBefore(jwtAuthenticationTokenFilter(),UsernamePasswordAuthenticationFilter.class);
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
