package com.example.exercise.model;

import lombok.Data;

import javax.swing.text.TabSet;
import java.awt.*;

@Data
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType{
        CHAT,JOIN,LEAVE
    }

}
