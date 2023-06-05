package com.example.exstudy.data.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.exstudy.data.model.FruitModel;

@Entity(tableName = "all_fruits_table")
public class FruitEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int image;
    private int defPrice;
    private int quantity;

    public FruitEntity(String name, int image, int defPrice, int quantity) {
        this.name = name;
        this.image = image;
        this.defPrice = defPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public FruitModel toModel(){
        return new FruitModel(name, image, defPrice, quantity);
    }
}
