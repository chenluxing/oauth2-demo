package com.lxc.oauth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auth chenlx
 */
@Controller
public class CommonController {

    @Autowired
    AuthorizationServerTokenServices tokenServices;
    @Autowired
    ClientDetailsService clientDetailsService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }

}
