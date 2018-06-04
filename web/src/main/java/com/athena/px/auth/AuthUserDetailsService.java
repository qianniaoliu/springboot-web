package com.athena.px.auth;

import com.athena.px.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/24 10:29
 */
@Component
public class AuthUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUsername(s);
        sysUser.setPassowrd("1");
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);
        sysUser.setAuthorities(authorities);
        return sysUser;
    }
}
