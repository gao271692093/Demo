package com.glg.ekwtest;

public class Item {
    private String message;
    private String right;

    public Item(String message, String right) {
        this.message = message;
        this.right = right;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
