package com.joshmlwood.springcloudkubernetesconfigdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurableController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurableController.class);

    private String externalConfig;

    public ConfigurableController(@Value("${app.config}") String externalConfig) {
        this.externalConfig = externalConfig;
        LOGGER.info(String.format("Current configuration is from: %s", externalConfig));
    }

    @GetMapping("/")
    public String getConfig() {
        return externalConfig;
    }
}
