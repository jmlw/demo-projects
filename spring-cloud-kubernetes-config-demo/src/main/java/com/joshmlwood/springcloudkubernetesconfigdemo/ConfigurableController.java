package com.joshmlwood.springcloudkubernetesconfigdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigurableController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurableController.class);

    private String externalConfig;
    private String environmentVariable;

    public ConfigurableController(
            @Value("${app.config}") String externalConfig,
            @Value("${app.environmentVariable}") String environmentVariable
    ) {
        this.externalConfig = externalConfig;
        this.environmentVariable = environmentVariable;
        LOGGER.info(String.format("app.config: %s\napp.environmentVariable: %s", externalConfig, environmentVariable));
    }

    @GetMapping("/")
    public Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("app.config", externalConfig);
        config.put("app.environmentVariable", environmentVariable);
        return config;
    }
}
