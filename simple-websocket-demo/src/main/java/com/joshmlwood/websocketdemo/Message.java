package com.joshmlwood.websocketdemo;

import java.time.LocalDateTime;

public class Message {
    private String from;
    private String message;
    private LocalDateTime timeStamp;

    public Message() {
        // required for Jackson
    }

    public Message(String from, String message, LocalDateTime timeStamp) {
        this.from = from;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
