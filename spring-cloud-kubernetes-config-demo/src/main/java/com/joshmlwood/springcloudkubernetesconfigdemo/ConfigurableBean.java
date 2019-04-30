package com.joshmlwood.springcloudkubernetesconfigdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConfigurableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurableBean.class);

    @Value("${external.config}")
    private String externalConfig;

    @PostConstruct
    public void printConfig() {
        LOGGER.info(String.format("Current configuration is from: %s", externalConfig));
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduledPrintConfig() {
        printConfig();
    }
}
