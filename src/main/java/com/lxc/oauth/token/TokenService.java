package com.lxc.oauth.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auth chenlx
 */
@Service
public class TokenService {

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private OAuth2RequestFactory requestFactory;
    @Autowired
    private TokenGranter defaultTokenGranter;

    public OAuth2AccessToken getAccessToken(String clientId, Map param) {
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

}
