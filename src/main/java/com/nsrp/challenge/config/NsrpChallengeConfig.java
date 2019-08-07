package com.nsrp.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NsrpChallengeConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}