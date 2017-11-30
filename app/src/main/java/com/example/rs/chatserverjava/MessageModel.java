package com.example.rs.chatserverjava;

/**
 * Created by rs on 12/1/17.
 */

public class MessageModel {
    private String name;

    public MessageModel(String name) {
        this.name = name;
    }

    public MessageModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
