package com.joshmlwood.springcloudkubernetesconfigdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringCloudKubernetesConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudKubernetesConfigDemoApplication.class, args);
    }

}
