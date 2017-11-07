package com.lxc.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "oauth2-demo";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
                .formLogin()
                .loginPage("/login")            // 设置登录页
                .permitAll()
                .successHandler(loginSuccessHandler())
            .and()
                .logout()
                .logoutSuccessUrl("/index")     // 设置退出成功默认页面
                .permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/index").permitAll()          // 访问首页无需权限认证
                .antMatchers("/demo*/**").permitAll()       // 所有登录和未登录人员都可以访问
                .antMatchers("/api/**").authenticated()     // 配置api访问控制，必须认证过后才可以访问
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")   // 管理员权限才可访问/admin,否则提示403
                .anyRequest().authenticated()      // 其他所有资源都需要权限认证

//            .and()
//                .httpBasic()
            .and().csrf().disable()       // 禁止跨域访问
        ;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
