package com.example.prm392;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "game_records")
public class GameRecord {
    @PrimaryKey
    @NonNull
    int id;
    String des;

    public GameRecord(int id, String des) {
        this.id = id;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
