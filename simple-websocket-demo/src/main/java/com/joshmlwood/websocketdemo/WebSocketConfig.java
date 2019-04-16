package com.joshmlwood.websocketdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * This enables a simple (in-memory) message broker for our application.
         * The `/topic` designates that any destination prefixed with `/topic`
         * will be routed back to the client.
         * It's important to keep in mind, this will not work with more than one
         * application instance, and it does not support all of the features a
         * full message broker like RabbitMQ, ActiveMQ, etc... provide.
         */
        registry.enableSimpleBroker("/topic");
        /*
         * The application destination prefix `/app` designates the broker to send
         * messages prefixed with `/app` to our `@MessageMapping`s.
         */
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         * This configures a STOMP (Simple Text Oriented Messaging Protocol)
         * endpoint for our websocket to be hosted on
         */
        registry.addEndpoint("/websocket");
        /*
         * This configures an endpoint with a fallback for SockJS in case the
         * client (an old browser) doesn't support WebSockets natively
         */
        registry.addEndpoint("/sockjs")
                .withSockJS();
    }
}
