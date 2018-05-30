package com.lxc;

import com.lxc.oauth.Oauth2DemoApplication;
import com.lxc.oauth.token.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2DemoApplication.class)
public class TokenServiceTest {

    @Inject
    private TokenService tokenService;

    @Test
    public void testGetAccessToken() {
        String clientId = "client_2";
        Map param = new HashMap();
        param.put("client_id", clientId);
        param.put("grant_type", "password");
        param.put("password", "123456");
        System.out.println(tokenService.getAccessToken(clientId, param));
    }

}
