package com.glg.headerintoplist;

public class Item {

    private String name;

    private int itemId;

    public Item(String name, int imageId) {
        this.name  = name;
        this.itemId = imageId;
    }

    public String getName() {
        return this.name;
    }

    public int getImageId() {
        return this.itemId;
    }
}
