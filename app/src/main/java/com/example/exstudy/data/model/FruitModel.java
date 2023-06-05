package com.example.exstudy.data.model;

public class FruitModel {
    private String name;
    private int image;
    private int timeToGrow;
    private int quantity;

    public FruitModel(String name, int image, int timeToGrow, int quantity) {
        this.name = name;
        this.image = image;
        this.timeToGrow = timeToGrow;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTimeToGrow() {
        return timeToGrow;
    }

    public void setTimeToGrow(int timeToGrow) {
        this.timeToGrow = timeToGrow;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
