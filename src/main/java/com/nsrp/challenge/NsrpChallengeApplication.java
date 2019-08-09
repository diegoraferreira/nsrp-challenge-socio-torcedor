package com.nsrp.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@EnableJpaRepositories("com.nsrp.challenge.repository")
@ComponentScan("com.nsrp.challenge")
public class NsrpChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NsrpChallengeApplication.class, args);
    }
}
