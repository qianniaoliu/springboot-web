package com.athena.px.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.athena.px.entity.SysUser;
import com.iu.sl.api.UserService;
import com.iu.sl.contants.SLResponseContants;
import com.iu.sl.model.ResponseUser;
import com.iu.sl.pojo.SLRequest;
import com.iu.sl.pojo.SLResponse;
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

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser(s);
        sysUser.setId(1L);
        sysUser.setPassowrd(validUser(s));
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);
        sysUser.setAuthorities(authorities);
        return sysUser;
    }

    public String validUser(String username){
        SLRequest slRequest = new SLRequest();
        slRequest.setLoginid(username);
        ResponseUser slResponse = (ResponseUser) userService.doLogin(slRequest);
        if(slResponse.getCode().equals(SLResponseContants.LOGIN_SUCCESS)){
            return slResponse.getPassword();
        }else{
            return null;
        }
    }
}
