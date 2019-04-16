package com.joshmlwood.websocketdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonConfig.class);

    @Autowired
    public JacksonConfig(ObjectMapper objectMapper) {
        /*
         * Customize the default ObjectMapper to register the java time module for
         * proper serialization and deserialization of java LocalDateTime
        */
        objectMapper.registerModule(new JavaTimeModule());
        LOGGER.info("Registered JavaTimeModule with Jackson ObjectMapper");
    }
}
