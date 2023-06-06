package com.example.exstudy.data.model;

import com.example.exstudy.data.room.FruitEntity;

public class FruitModel {
    private String name;
    private int image;
    private int defPrice;
    private int quantity;

    public FruitModel(String name, int image, int defPrice, int quantity) {
        this.name = name;
        this.image = image;
        this.defPrice = defPrice;
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

    public int getDefPrice() {
        return defPrice;
    }

    public void setDefPrice(int defPrice) {
        this.defPrice = defPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public FruitEntity toEntity(){
        return new FruitEntity(name, image, defPrice, quantity);
    }
}
