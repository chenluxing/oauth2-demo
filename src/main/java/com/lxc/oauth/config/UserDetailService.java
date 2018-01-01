package com.lxc.oauth.config;

import com.lxc.oauth.config.domain.OUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 */
@Service
public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 从数据库校验用户数据
        String userName = "admin";
        if (!userName.equals(username)) {
            throw new UsernameNotFoundException(String.format("用户信息不存在%s", username));
        }
        String password = "123456";
        List<String> auths = new ArrayList<>();
        auths.add("ROLE_ADMIN");
        auths.add("ROLE_USER");
        List<GrantedAuthority> grantedAuthorities = auths.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
        OUser user = new OUser(userName, password, grantedAuthorities);
        user.setOrganName("测试机构名称");
        return user;
    }
}
