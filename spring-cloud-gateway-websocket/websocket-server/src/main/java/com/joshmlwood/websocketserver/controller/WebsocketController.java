package com.joshmlwood.websocketserver.controller;

import com.joshmlwood.websocketserver.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
public class WebsocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketController.class);
    private static final Random RANDOM = new Random();
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Value("${server.port}")
    private String port;

    @Autowired
    public WebsocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/incoming")
    @SendTo("/topic/outgoing")
    public String incoming(Message message) {
        LOGGER.info(String.format("received message: %s", message));
        return String.format("Application on port %s responded to your message: \"%s\"", port, message.getMessage());
    }

    @Scheduled(fixedRate = 15000L)
    public void timed() {
        try {
            // simulate randomness in our timed responses to the client
            Thread.sleep(RANDOM.nextInt(10) * 1000);
            LOGGER.info("sending timed message");
            simpMessagingTemplate.convertAndSend(
                "/topic/outgoing",
                String.format("Application on port %s pushed a message!", port)
            );
        } catch (InterruptedException exception) {
            LOGGER.error(String.format("Thread sleep interrupted. Nested exception %s", exception.getMessage()));
        }
    }
}
