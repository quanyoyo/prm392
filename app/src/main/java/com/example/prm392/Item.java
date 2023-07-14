package com.example.prm392;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item")
public class Item {
    @NonNull
    @PrimaryKey
    private String name;
    private int imageResource;

    public Item(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
