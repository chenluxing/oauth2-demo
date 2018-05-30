package com.lxc.oauth.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private OAuth2RequestFactory requestFactory;
    @Autowired
    private TokenGranter defaultTokenGranter;
    @Autowired
    TokenGranter resourceOwnerPasswordTokenGranter;

    public OAuth2AccessToken getAccessToken(String clientId, Map param) {
        param.put("username", "admin");
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = requestFactory.createTokenRequest(param, clientDetails);
        return defaultTokenGranter.grant(tokenRequest.getGrantType(), tokenRequest);
    }

    @Bean
    public OAuth2RequestFactory requestFactory(ClientDetailsService clientDetailsService) {
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    @Bean
    public DefaultTokenGranter defaultTokenGranter(UserDetailsService userDetailService,
                                                   AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                                                   OAuth2RequestFactory requestFactory) {
        return new DefaultTokenGranter(userDetailService, tokenServices, clientDetailsService, requestFactory, DefaultTokenGranter.GRANT_TYPE);
    }

    @Bean
    public ResourceOwnerPasswordTokenGranter resourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                                                                               OAuth2RequestFactory requestFactory) {
        return new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory);
    }

}
