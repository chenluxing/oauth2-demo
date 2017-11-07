package com.lxc.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/*@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient*/
@SpringBootApplication
public class Oauth2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2DemoApplication.class, args);
    }
}
