package com.example.exstudy.data.model;

public class SeedModel {

    private String name;
    private int image;
    private int timeToGrow;
    private int quantity;

    public SeedModel(String name, int image, int timeToGrow, int quantity) {
        this.name = name;
        this.image = image;
        this.timeToGrow = timeToGrow;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getTimeToGrow() {
        return timeToGrow;
    }

    public int getQuantity() {
        return quantity;
    }
}
