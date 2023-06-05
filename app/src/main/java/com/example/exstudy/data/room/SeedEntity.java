package com.example.exstudy.data.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.exstudy.data.model.SeedModel;

@Entity(tableName = "all_seeds_table")
public class SeedEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int image;
    private int timeToGrow;
    private int quantity;

    public SeedEntity(String name, int image, int timeToGrow, int quantity) {
        this.name = name;
        this.image = image;
        this.timeToGrow = timeToGrow;
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

    public SeedModel toModel(){
        return new SeedModel(name, image, timeToGrow, quantity);
    }
}
