package com.lxc.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * 认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_1")
                .scopes("select")
                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit")
                .accessTokenValiditySeconds(3600 * 24 * 30)
                .refreshTokenValiditySeconds(3600 * 24 * 30 * 2)
                .and()
                .withClient("client_2")
                .scopes("select")
                .authorizedGrantTypes("password", "refresh_token", "implicit", "default")
                .accessTokenValiditySeconds(3600 * 24 * 30)
                .refreshTokenValiditySeconds(3600 * 24 * 30 * 2)
                .and()
                .withClient("client_3")
                .scopes("select")
                .secret("123456")
                .authorizedGrantTypes("client_credentials", "refresh_token", "implicit")
                .accessTokenValiditySeconds(3600 * 24 * 30)
                .refreshTokenValiditySeconds(3600 * 24 * 30 * 2)
                ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());
        endpoints.accessTokenConverter(jwtAccessTokenConverter());  // 必须要写，不然生成不了jwt；而是原始的token
        endpoints.reuseRefreshTokens(false);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                new ClassPathResource("serverKeystore.jks"), "123456".toCharArray())
                .getKeyPair("alias1");
        converter.setKeyPair(keyPair);
        return converter;
    }

}
