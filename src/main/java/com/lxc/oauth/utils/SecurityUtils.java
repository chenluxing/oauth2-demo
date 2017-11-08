package com.lxc.oauth.utils;

import com.lxc.oauth.config.domain.OUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 */
public class SecurityUtils {

    /**
     * 获取当前用户名
     * @return
     */
    public static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof OUser) {
                // 此处举个例子，可以在UserDetailService中自己封装用户信息。
                OUser oUser = (OUser) authentication.getPrincipal();
                userName = oUser.getUsername();
            } else {
                userName = (String)authentication.getPrincipal();
            }
        }
        return userName;
    }

    /**
     * 权限判断
     * @param authority
     * @return
     */
    public static boolean hasAuthority(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return authentication != null ? authentication.getAuthorities().stream().anyMatch(
                (grantedAuthority) -> {
                    return grantedAuthority.getAuthority().equals(authority);
                }) : false;
    }

}
