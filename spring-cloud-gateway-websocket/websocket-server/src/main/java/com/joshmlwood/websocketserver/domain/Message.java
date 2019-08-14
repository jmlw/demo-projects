package com.joshmlwood.websocketserver.domain;

public class Message {
    private String message;

    public Message() {
        // Required for Jackson
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
            "message='" + message + '\'' +
            '}';
    }
}
