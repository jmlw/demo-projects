package com.joshmlwood.websocketserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SocketUtils;

@Configuration
public class WebServerFactoryConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Value("${websocketserver.min-port:8080}")
    private Integer minPort;
    @Value("${websocketserver.max-port:9080}")
    private Integer maxPort;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
        factory.setPort(port);
        System.getProperties().put("websocketserver.port", port);
    }
}
