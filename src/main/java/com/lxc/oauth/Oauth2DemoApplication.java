package com.lxc.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class Oauth2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2DemoApplication.class, args);
        System.out.println("this is a test222222");
    }
}
