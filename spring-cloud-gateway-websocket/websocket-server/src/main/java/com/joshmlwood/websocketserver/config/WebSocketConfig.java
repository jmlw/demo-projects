package com.joshmlwood.websocketserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${broker.relay.host}")
    private String brokerRelayHost;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * all subscription data (outgoing / responses) will send data to a URL the client can subscribe to
         * that begins with `/topic`. For example, a method with @SendTo("/outgoing") will allow a client
         * to subscribe on "/topic/outgoing" to receive messages from the websocket websocketserver
         */
        registry.enableStompBrokerRelay("/queue", "/topic")
            .setRelayHost(brokerRelayHost);
        /*
         * all destinations (request mappings in mvc / rest terms) will accept requests from destinations
         * prefixed with `/app`. For example, a @MessageMapping("/hello") will receive messages from the
         * client when the client sends a message to "/app/hello"
         */
        registry.setApplicationDestinationPrefixes("/app");
//        registry.setPathMatcher(new AntPathMatcher("."));
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         * This configures the endpoint, or the URL, that the application will be serving its websocket
         * connection from. All clients will use this URL to open a connection to this websocket websocketserver
         */
        registry.addEndpoint("/websocket")
            .setAllowedOrigins("*")
            .withSockJS();
    }
}
